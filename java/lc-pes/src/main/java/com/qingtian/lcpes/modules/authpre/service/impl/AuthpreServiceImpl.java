package com.qingtian.lcpes.modules.authpre.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.nimbusds.jose.JWSObject;
import com.qingtian.lcpes.base.util.IPUtils;
import com.qingtian.lcpes.modules.authpre.entity.AcUserEntity;
import com.qingtian.lcpes.modules.authpre.service.AcUserService;
import com.qingtian.lcpes.modules.authpre.service.AuthpreService;
import com.qingtian.lcpes.modules.authpre.util.AesUtil;
import com.qingtian.lcpes.modules.authpre.vo.LoginUser;
import com.qingtian.lcpes.modules.sys.vo.SysLogVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;


@Service
@Slf4j
public class AuthpreServiceImpl implements AuthpreService {

    public static final String AES_KEY = "12345678901234561234567890123456";
    @Value("${qingtian.auth.address}")
    private String authAddress;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private AcUserService acUserService;

    @Value("${qingtian.auth.token}")
    private String authTokenVal;

    @Override
    public String login(String loginId, String password, HttpServletResponse response) throws IOException {
        //权限平台url
        //dev:
        String url = authAddress + "/ac/login/dologin.action";
        //String url = "http://10.88.246.110:9001/ac/login/doLoginForNewPortal.action";

        //pro:
        //String url = "http://10.188.57.73/ac/login/dologin.action";
        //String url = "http://10.188.57.73/ac/login/doLoginForNewPortal.action";

        //密码解密
        password = AesUtil.decryptStr(password, AES_KEY);
        HttpClient httpClient = new HttpClient();
        PostMethod postMethod = new PostMethod(url);
        postMethod.addParameter("username", loginId);
        postMethod.addParameter("password", password);
        // LOGGER.info("++++++++++登录url:+++++++++++++++++" + url + "?username=" + loginId + "&password=" + password);
        //默认的重试策略
        postMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler());
        //设置超时时间
        postMethod.getParams().setParameter(HttpMethodParams.SO_TIMEOUT, 5000);
        int httpStatus = httpClient.executeMethod(postMethod);
        // LOGGER.info("-------httpStatus:-------:" + httpStatus);
        String str = postMethod.getResponseBodyAsString();
        // LOGGER.info("-------jsonstr-------:" + str);
        JSONObject jsonobj = JSON.parseObject(str);

        String res = jsonobj.getString("success");
        if (res.equalsIgnoreCase("false")) {
            setLoginLog(loginId, 2);
            return null;
        }
        else {
            //存储token ,key规约 token:10011810
            this.stringRedisTemplate.opsForValue().set("authtoken:" + loginId, str, 60, TimeUnit.MINUTES);

            //存储数据权限到redis
            acUserService.setBizToRedis(loginId);

            //查看token
            //this.getTokenFromRedis(loginId);

            this.setTokenToCookies(loginId, response);
            setLoginLog(loginId, 1);

        }
        //用户信息存取
        /* 每次登陆重新获取用户、权限信息
        if (!stringRedisTemplate.hasKey("user:" + loginId)) {
            return this.getUserInfo(loginId, httpClient);
        } else {
            return stringRedisTemplate.opsForValue().get("user:" + loginId);
        }*/
        //return this.getUserInfo(loginId, httpClient);
        return str;

    }


    @Override
    public String splogin(String loginSrc, String loginId, String password) throws IOException {
        //权限平台url
        //dev:
        //String url = authAddress + "/ac/login/dologin.action";
        String url = authAddress + "/ac/login/doLoginForNewPortal.action";
        //pro:
        //String url = "http://10.188.57.73/ac/login/dologin.action";
        //String url = "http://10.188.57.73/ac/login/doLoginForNewPortal.action";

        //密码解密
        password = AesUtil.decryptStr(password, AES_KEY);
        HttpClient httpClient = new HttpClient();
        PostMethod postMethod = new PostMethod(url);
        postMethod.addParameter("username", loginSrc);
        postMethod.addParameter("password", password);
        //默认的重试策略
        postMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler());
        //设置超时时间
        postMethod.getParams().setParameter(HttpMethodParams.SO_TIMEOUT, 5000);
        int httpStatus = httpClient.executeMethod(postMethod);
        // LOGGER.info("-------httpStatus:-------:" + httpStatus);
        String str = postMethod.getResponseBodyAsString();
        // LOGGER.info("-------jsonstr-------:" + str);
        JSONObject jsonobj = JSON.parseObject(str);

        String res = jsonobj.getString("success");
        if (res.equalsIgnoreCase("false")) {
            setLoginLog(loginSrc, 2);
            return null;
        }
        else {
            String tokenStr = jsonobj.getJSONObject("data").getJSONObject("userBean").getString("token");
            //存储token ,key规约 token:10011810
            this.stringRedisTemplate.opsForValue().set("authtoken:" + loginId, tokenStr, 60, TimeUnit.MINUTES);

            //存储数据权限到redis
            acUserService.setBizToRedis(loginId);

            setLoginLog(loginId, 1);

        }
        //用户信息存取
        /* 每次登陆重新获取用户、权限信息
        if (!stringRedisTemplate.hasKey("user:" + loginId)) {
            return this.getUserInfo(loginId, httpClient);
        } else {
            return stringRedisTemplate.opsForValue().get("user:" + loginId);
        }*/
        //return this.getUserInfo(loginId, httpClient);
        return str;

    }

    /*
     * 保存登录日志
     * @param loginId
     * @param flag 1成功  2失败
     */
    void setLoginLog(String loginId, Integer flag) {

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String url = request.getRequestURL().toString() + "?" + request.getQueryString();
        String ip = IPUtils.getIp(request);

        //记录登录日志
        SysLogVO sysLogVO = new SysLogVO();
        // 任务开始创建时间
        long startTime = System.currentTimeMillis();
        sysLogVO.setUserId(loginId);//获取用户id
        sysLogVO.setLogType("1");//日志类型
        sysLogVO.setLogContent("权限集成");
        sysLogVO.setOperateType("登录");
        sysLogVO.setRequestUrl(url);
        sysLogVO.setIp(ip);
        startTime = System.currentTimeMillis();//创建时间设置
        sysLogVO.setCreatedDt(LocalDateTime.now());//设置创建时间
        // 控制台显示
        // LOGGER.info("操作用户：{}"+sysLogVO.getUserId());
        sysLogVO.setResultMsg(1 == flag ? "成功" : "失败");
        sysLogVO.setCostTime(BigDecimal.valueOf(System.currentTimeMillis() - startTime));//设置耗时
    }

    /*
     * 保存登出日志
     * @param loginId
     * @param flag 1成功  2失败
     */
    void setLogoutLog(String loginId, Integer flag) {

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String url = request.getRequestURL().toString() + "?" + request.getQueryString();
        String ip = IPUtils.getIp(request);

        //记录登出日志
        SysLogVO sysLogVO = new SysLogVO();
        //任务开始创建时间
        long startTime = System.currentTimeMillis();
        sysLogVO.setUserId(loginId);//获取用户id
        sysLogVO.setLogType("1");//日志类型
        sysLogVO.setLogContent("权限集成");
        sysLogVO.setOperateType("登出");
        sysLogVO.setRequestUrl(url);
        sysLogVO.setIp(ip);
        startTime = System.currentTimeMillis();//创建时间设置
        sysLogVO.setCreatedDt(LocalDateTime.now());//设置创建时间
        sysLogVO.setResultMsg(1 == flag ? "成功" : "失败");
        //设置消耗时间
        long endTime = System.currentTimeMillis();
        //转换成秒
        Long costTime = ((endTime - startTime));
        sysLogVO.setCostTime(BigDecimal.valueOf(costTime));//设置耗时
    }

    @Override
    public String getLoginId(HttpServletRequest request) {
        String token = request.getHeader("AuthUserToken");
        try {
            JWSObject jwsObject = JWSObject.parse(token);
            net.minidev.json.JSONObject jsonObject = jwsObject.getPayload().toJSONObject();
            JSONObject jo = JSON.parseObject(jsonObject.toString());
            String sub = jo.getString("sub");
            if (sub.length() <= 12) {
                return sub;
            }
            JSONObject joLoginId = JSON.parseObject(sub);
            return joLoginId.getString("loginId");
        }
        catch (ParseException ex) {
            ex.printStackTrace();
            return null;
        }

    }

    @Override
    public String getTokenFromRedis(String loginId) {
        String tokenStr = stringRedisTemplate.opsForValue().get("authtoken:" + loginId);
        if (isJSON2(tokenStr)) {
            JSONObject jsonobj = JSON.parseObject(tokenStr);
            String res = jsonobj.getString("data");
            JSONObject jsonobjt = JSON.parseObject(res);
            String token = jsonobjt.getString("token");
            return token;
        }
        else {
            return tokenStr;
        }
    }

    public static boolean isJSON2(String str) {
        boolean result = false;
        try {
            Object obj = JSON.parse(str);
            result = true;
        }
        catch (Exception e) {
            result = false;
        }
        return result;
    }


    @Override
    public LoginUser getUserInfo(String loginId) {
        String userInfo = stringRedisTemplate.opsForValue().get("user:" + loginId);
        JSONObject jsonobj = JSON.parseObject(userInfo);
        try {
            String data = jsonobj.getString("data");
            LoginUser loginUser = new LoginUser();
            AcUserEntity acUser = this.acUserService.getAcUserByLoginId(loginId);
            if (acUser != null) {
                loginUser.setUserSid(acUser.getSid());
            }
            loginUser.setLoginId(loginId);
            loginUser.setOrgCode(JSON.parseObject(data).getString("orgCode"));
            loginUser.setOrgName(JSON.parseObject(data).getString("orgName"));
            loginUser.setPositionCode(JSON.parseObject(data).getString("positionCode"));
            loginUser.setPositionName(JSON.parseObject(data).getString("positionName"));
            loginUser.setUsername(JSON.parseObject(data).getString("username"));
            return loginUser;
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }

    }

    @Override
    public String getAuthInfoFromRedis(String loginId, String sysId) throws IOException {
        //人员和权限接口url
        //pro:
        //String url = "http://10.188.57.73/ac/ac-user/getUserInfoAndResourceAssignTree.action";
        //dev:
        String url = authAddress + "/ac/ac-user/getUserInfoAndResourceAssignTree.action";
        HttpClient httpClient = new HttpClient();
        PostMethod postMethod = new PostMethod(url);
        postMethod.addParameter("usercode", loginId);
        postMethod.addParameter("appCode", sysId);
        //Authorization 允许访问授权平台
        postMethod.setRequestHeader("Authorization", this.getTokenFromRedis(loginId));
        //默认的重试策略
        postMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler());
        //设置超时时间
        postMethod.getParams().setParameter(HttpMethodParams.SO_TIMEOUT, 5000);
        int httpStatus = httpClient.executeMethod(postMethod);
        // LOGGER.info("-------获取人员、权限信息-------");
        // LOGGER.info("-------httpStatus-------:" + httpStatus);
        //人员、权限信息字符串合集
        String str = postMethod.getResponseBodyAsString();
        //stringRedisTemplate.opsForValue().set("user:" + username, str,30, TimeUnit.MINUTES);
        //存储用户与权限 ,key规约 user:10011810
        stringRedisTemplate.opsForValue().set("user:" + loginId, str);


        //同步auth库用户信息到itmp库
        if (authTokenVal.equalsIgnoreCase("YES")) {
            acUserService.asynUserInfo(loginId);
        }

        return stringRedisTemplate.opsForValue().get("user:" + loginId);
    }

    @Override
    public String getUserBtnAuth(String loginId) throws IOException {
        //人员按钮权限接口url
        //pro:
        //String url = "http://10.188.57.73/ac/ac-resource/getUserAssignedResourceTree.action";
        //dev:
        String url = authAddress + "/ac/ac-resource/getUserAssignedResourceTree.action";
        HttpClient httpClient = new HttpClient();
        PostMethod postMethod = new PostMethod(url);
        postMethod.addParameter("loginId", loginId);
        postMethod.addParameter("appCode", "TMP");
        //resType : 2 按钮标识
        postMethod.addParameter("resType", "2");
        //Authorization 允许访问授权平台
        postMethod.setRequestHeader("Authorization", this.getTokenFromRedis(loginId));
        //默认的重试策略
        postMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler());
        //设置超时时间
        postMethod.getParams().setParameter(HttpMethodParams.SO_TIMEOUT, 5000);
        int httpStatus = httpClient.executeMethod(postMethod);
        String str = postMethod.getResponseBodyAsString();
        //stringRedisTemplate.opsForValue().set("user:" + username, str,30, TimeUnit.MINUTES);
        //存储用户按钮权限 ,key规约 user:btn: 10011810
        stringRedisTemplate.opsForValue().set("user:btn:" + loginId, str);

        return stringRedisTemplate.opsForValue().get("user:btn:" + loginId);
    }

    @Override
    public String getUserPageAuth(String loginId) throws IOException {
        //人员菜单权限接口url
        //pro:
        //String url = "http://10.188.57.73/ac/ac-resource/getUserAssignedResourceTree.action";
        //dev:
        String url = authAddress + "/ac/ac-resource/getUserAssignedResourceTree.action";
        HttpClient httpClient = new HttpClient();
        PostMethod postMethod = new PostMethod(url);
        postMethod.addParameter("loginId", loginId);
        postMethod.addParameter("appCode", "TMP");
        //resType : 2 按钮标识
        postMethod.addParameter("resType", "1");
        //Authorization 允许访问授权平台
        postMethod.setRequestHeader("Authorization", this.getTokenFromRedis(loginId));
        //默认的重试策略
        postMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler());
        //设置超时时间
        postMethod.getParams().setParameter(HttpMethodParams.SO_TIMEOUT, 5000);
        int httpStatus = httpClient.executeMethod(postMethod);
        String str = postMethod.getResponseBodyAsString();
        //stringRedisTemplate.opsForValue().set("user:" + username, str,30, TimeUnit.MINUTES);
        //存储用户按钮权限 ,key规约 user:page: 10011810
        stringRedisTemplate.opsForValue().set("user:page:" + loginId, str);

        return stringRedisTemplate.opsForValue().get("user:page:" + loginId);
    }

    @Override
    public Boolean logout() {
        Boolean result = false;
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String loginId = getLoginId(request);
        try {
            if (stringRedisTemplate.hasKey("authtoken:" + loginId)) {
                stringRedisTemplate.delete("authtoken:" + loginId);
            }

            setLogoutLog(loginId, 1);
            result = true;
        }
        catch (Exception e) {
            setLogoutLog(loginId, 2);
        }
        return result;
    }

    @Override
    public void reset(String loginId) {
        if (stringRedisTemplate.hasKey("authtoken:" + loginId)) {
            stringRedisTemplate.expire("authtoken:" + loginId, 60, TimeUnit.MINUTES);
        }
    }


    void setTokenToCookies(String loginId, HttpServletResponse response) {
        String token = this.getTokenFromRedis(loginId);
        Cookie e = new Cookie("AuthUserToken", token);
        //token存入cookie
        response.addCookie(e);
        Cookie u = new Cookie("LoginId", loginId);
        //loginId存入cookie
        response.addCookie(u);

    }


    /**
     * 从权限平台获取人员和权限信息
     * 存储到redis
     *
     * @param username
     * @param httpClient
     * @return
     * @throws IOException
     */
    public String getUserInfo(String username, HttpClient httpClient) throws IOException {
        //人员和权限接口url
        //pro:
        //String url = "http://10.188.57.73/ac/ac-user/getUserInfoAndResourceAssignTree.action";
        //dev:
        String url = authAddress + "/ac/ac-user/getUserInfoAndResourceAssignTree.action";
        PostMethod postMethod = new PostMethod(url);
        postMethod.addParameter("usercode", username);
        postMethod.addParameter("appCode", "TMP");
        //默认的重试策略
        postMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler());
        //设置超时时间
        postMethod.getParams().setParameter(HttpMethodParams.SO_TIMEOUT, 5000);
        int httpStatus = httpClient.executeMethod(postMethod);
        // LOGGER.info("-------获取人员、权限信息-------");
        // LOGGER.info("-------httpStatus-------:" + httpStatus);
        //人员、权限信息字符串合集
        String str = postMethod.getResponseBodyAsString();
        //stringRedisTemplate.opsForValue().set("user:" + username, str,30, TimeUnit.MINUTES);
        //存储用户与权限 ,key规约 user:10011810
        stringRedisTemplate.opsForValue().set("user:" + username, str);
        return stringRedisTemplate.opsForValue().get("user:" + username);
    }

}
