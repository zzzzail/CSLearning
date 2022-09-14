package com.qingtian.lcpes.modules.authpre.controller;

import com.alibaba.fastjson.JSONObject;
import com.qingtian.lcpes.base.web.JsonRequest;
import com.qingtian.lcpes.base.web.JsonResponse;
import com.qingtian.lcpes.modules.authpre.service.AuthpreService;
import com.qingtian.lcpes.modules.authpre.util.LoginUtil;
import com.qingtian.lcpes.modules.authpre.vo.LoginUser;
import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author J
 * @date 2022/4/2 16:08
 */
@RestController
@RequestMapping("/itmp/authpre")
public class AuthpreController {

    private final static Logger LOGGER = LoggerFactory.getLogger(AuthpreController.class);

    @Autowired
    private AuthpreService authpreService;

    /*
     * 1.1 登录
     * @param jsonRequest
     * @param response
     * @return com.qingtian.starter.base.JsonResponse
     * @Author CaoShuJie
     * @Date 2022/8/3 11:10
     */
    @RequestMapping(value = "/login")
    @ResponseBody
    public JsonResponse login(@RequestBody JsonRequest<LoginUser> jsonRequest, HttpServletResponse response) {
        LoginUser loginUser = jsonRequest.getReqBody();
        JsonResponse<Object> jsonResponse = new JsonResponse<>();
        if (StringUtils.isBlank(loginUser.getLoginId()) || StringUtils.isBlank(loginUser.getPassword())) {
            jsonResponse.setRetCode("500");
            jsonResponse.setRetDesc("请输入用户名，密码");
            return jsonResponse;
        }
        try {
            String user = this.authpreService.login(loginUser.getLoginId(), loginUser.getPassword(), response);
            if (StringUtils.isBlank(user)) {
                jsonResponse.setRetCode("500");
                jsonResponse.setRetDesc("用户名，密码错误");
                return jsonResponse;
            }
            jsonResponse.setRspBody(JSONObject.parseObject(user));
            return jsonResponse;
        }
        catch (Exception ex) {
            ex.printStackTrace();
            jsonResponse.setRetCode("500");
            jsonResponse.setRetDesc(ex.getMessage());
            return jsonResponse;
        }
    }

    /*
     * 1.2 获取人员、菜单权限
     *     按钮权限前台过滤掉
     * @param request ,从中获取用户id
     * @param sysid 系统编号，默认TMP
     * @return com.qingtian.starter.base.JsonResponse
     * @Author CaoShuJie
     * @Date 2022/8/3 11:10
     */
    @RequestMapping(value = "/getAuthInfo")
    @ResponseBody
    public JsonResponse getAuthInfo(HttpServletRequest request, String sysId) {

        String loginId = authpreService.getLoginId(request);
        JsonResponse<Object> jsonResponse = new JsonResponse<>();

        sysId = sysId == null ? "TMP" : sysId;
        try {
            String user = this.authpreService.getAuthInfoFromRedis(loginId, sysId);
            jsonResponse.setRspBody(JSONObject.parseObject(user));
            return jsonResponse;
        }
        catch (Exception ex) {
            ex.printStackTrace();
            jsonResponse.setRetCode("500");
            jsonResponse.setRetDesc(ex.getMessage());
            return jsonResponse;
        }
    }


    /*
     * 1.3 获取人员按钮权限
     * @param request
     * @return com.qingtian.starter.base.JsonResponse
     * @Author CaoShuJie
     * @Date 2022/8/3 11:11
     */
    @RequestMapping(value = "/getAuthBtnInfo")
    @ResponseBody
    public JsonResponse getAuthBtnInfo(HttpServletRequest request) {

        String loginId = authpreService.getLoginId(request);
        JsonResponse<Object> jsonResponse = new JsonResponse<>();
        try {
            String btn = this.authpreService.getUserBtnAuth(loginId);
            jsonResponse.setRspBody(JSONObject.parseObject(btn));
            return jsonResponse;
        }
        catch (Exception ex) {
            ex.printStackTrace();
            jsonResponse.setRetCode("500");
            jsonResponse.setRetDesc(ex.getMessage());
            return jsonResponse;
        }
    }

    /*
     * 1.4 获取人员菜单权限
     * @param request
     * @return com.qingtian.starter.base.JsonResponse
     * @Author CaoShuJie
     * @Date 2022/8/3 11:11
     */
    @RequestMapping(value = "/getAuthPageInfo")
    @ResponseBody
    public JsonResponse getAuthPageInfo(HttpServletRequest request) {

        String loginId = authpreService.getLoginId(request);
        JsonResponse<Object> jsonResponse = new JsonResponse<>();
        try {
            String page = this.authpreService.getUserPageAuth(loginId);
            jsonResponse.setRspBody(JSONObject.parseObject(page));
            return jsonResponse;
        }
        catch (Exception ex) {
            ex.printStackTrace();
            jsonResponse.setRetCode("500");
            jsonResponse.setRetDesc(ex.getMessage());
            return jsonResponse;
        }
    }


    @RequestMapping(value = "/testuser")
    public LoginUser testuser() {
        /*String loginId = "10011810";
        return this.authpreService.getUserInfo(loginId);*/
        LoginUser loginUser = new LoginUser();
        loginUser.setLoginId(LoginUtil.getLoginId());
        loginUser.setUsername(LoginUtil.getUserName());
        loginUser.setPositionCode(LoginUtil.getPositionCode());
        loginUser.setOrgName(LoginUtil.getOrgName());
        loginUser.setOrgCode(LoginUtil.getOrgCode());
        loginUser.setPositionName(LoginUtil.getPositionName());
        return loginUser;
    }

    @RequestMapping(value = "/testApi")
    @ResponseBody
    public JsonResponse testApi(HttpServletRequest request, HttpServletResponse response) throws IOException {
        LOGGER.info("-------testApi-------");
        String token = this.authpreService.getTokenFromRedis("10011810");
        HttpClient httpClient = new HttpClient();
        String username = "10011810";
        String url = "http://10.188.57.73/ac/ac-user/getUserInfoAndResourceAssignTree.action";
        PostMethod postMethod = new PostMethod(url);
        postMethod.addParameter("usercode", username);
        postMethod.addParameter("appCode", "AUTH");
        //Authorization 允许访问授权平台
        postMethod.setRequestHeader("Authorization", token);

        //默认的重试策略
        postMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler());
        //设置超时时间
        postMethod.getParams().setParameter(HttpMethodParams.SO_TIMEOUT, 5000);
        int httpStatus = httpClient.executeMethod(postMethod);
        LOGGER.info("-------httpStatus-------:" + httpStatus);


        //获得Cookies
      /*  Cookie cookies[] = httpClient.getState().getCookies();
        List<Cookie> cookiesList = Arrays.asList(cookies);
        LOGGER.info("-------cookiesList-------:" + cookiesList);
        Cookie e=new Cookie(null,"noname","AuthUserToken",token,null,false);
        cookiesList.add(e);*/
        Cookie e = new Cookie("AuthUserToken", token);
        response.addCookie(e);
        return (new JsonResponse()).success();
    }


    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    @ResponseBody
    public JsonResponse<Boolean> logout() {
        JsonResponse<Boolean> jsonResponse = new JsonResponse<>();
        try {
            Boolean result = authpreService.logout();
            jsonResponse.setRspBody(result);
        }
        catch (Exception e) {
            jsonResponse.setRetCode("500");
            jsonResponse.setRetDesc("登出失败");
        }
        return jsonResponse;
    }


    /*
     * 单点登录，接入
     * @param jsonRequest
     * @param response
     * @return com.qingtian.starter.base.JsonResponse
     * @Author CaoShuJie
     * @Date 2022/8/30 11:10
     */
    @RequestMapping(value = "/splogin")
    @ResponseBody
    public JsonResponse splogin(@RequestBody JsonRequest<LoginUser> jsonRequest) {
        JsonResponse<Object> jsonResponse = new JsonResponse<>();
        LoginUser loginUser = jsonRequest.getReqBody();
        if (StringUtils.isBlank(loginUser.getLoginId())
                || StringUtils.isBlank(loginUser.getPassword())
                || StringUtils.isBlank(loginUser.getLoginSrc())
        ) {
            jsonResponse.setRetCode("500");
            jsonResponse.setRetDesc("请输入用户名，密码");
            return jsonResponse;
        }
        try {
            String user = this.authpreService.splogin(loginUser.getLoginSrc(), loginUser.getLoginId(), loginUser.getPassword());

            if (StringUtils.isBlank(user)) {
                jsonResponse.setRetCode("500");
                jsonResponse.setRetDesc("用户名，密码错误");
                return jsonResponse;
            }
            jsonResponse.setRspBody(JSONObject.parseObject(user));
            return jsonResponse;
        }
        catch (Exception ex) {
            ex.printStackTrace();
            jsonResponse.setRetCode("500");
            jsonResponse.setRetDesc(ex.getMessage());
            return jsonResponse;
        }
    }


}
