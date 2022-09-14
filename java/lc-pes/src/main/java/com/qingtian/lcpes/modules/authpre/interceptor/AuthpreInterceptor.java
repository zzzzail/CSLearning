package com.qingtian.lcpes.modules.authpre.interceptor;

import com.qingtian.lcpes.base.web.JsonResponse;
import com.qingtian.lcpes.modules.authpre.service.AuthpreService;
import com.qingtian.lcpes.modules.authpre.util.LoginUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;


@Component
public class AuthpreInterceptor implements HandlerInterceptor {
    private final static Logger LOGGER = LoggerFactory.getLogger(AuthpreInterceptor.class);

    @Autowired
    private AuthpreService authpreService;

    @Value("${qingtian.auth.token}")
    private String authTokenVal;

    //处理请求之前
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // LOGGER.info("AuthpreInterceptor:---------preHandle");
        JsonResponse<String> jsonResponse = new JsonResponse<>();
        //登录后，前台存储AuthUserToken
        String valToken = request.getHeader("AuthUserToken");
        response.addHeader("content-type", "text/html;charset:UTF-8");
        String valLoginId = null;
        try {
            valLoginId = this.authpreService.getLoginId(request);
        }
        catch (Exception ex) {
            JsonResponse jsonResponse1 = errorJR(jsonResponse);
            jsonResponse1.setRetDesc("no loginId, please login again");
            PrintWriter pw = response.getWriter();
            response.setStatus(401);
            pw.write(errorStr(jsonResponse1));
            ex.printStackTrace();
            return false;
        }
        /*Cookie cookies[] = request.getCookies();
        if (cookies != null) {
            for (int i = 0; i < cookies.length; i++) {
                if (cookies[i].getName().equals("AuthUserToken")) {
                    valToken = cookies[i].getValue();
                }
                if (cookies[i].getName().equals("LoginId")) {
                    valLoginId = cookies[i].getValue();
                }
            }
        }*/
        //校验
        if (StringUtils.isBlank(valToken) || StringUtils.isBlank(valLoginId)) {
            JsonResponse jsonResponse2 = errorJR(jsonResponse);
            jsonResponse2.setRetDesc("Login failed, please login again");
            PrintWriter pw = response.getWriter();
            response.setStatus(401);
            pw.write(errorStr(jsonResponse2));
            return false;
        }
        String redisToken = authpreService.getTokenFromRedis(valLoginId);
        if (!redisToken.equalsIgnoreCase(valToken) && authTokenVal.equalsIgnoreCase("YES")) {
            JsonResponse jsonResponse3 = errorJR(jsonResponse);
            jsonResponse3.setRetDesc("Token failed, please login again");
            PrintWriter pw = response.getWriter();
            response.setStatus(401);
            pw.write(errorStr(jsonResponse3));
            return false;
        }

        //保存用户信息
        if (valLoginId.length() < 12) {
            LoginUtil.init(authpreService.getUserInfo(valLoginId));
        }

        authpreService.reset(valLoginId);

        return true;
    }

    public JsonResponse errorJR(JsonResponse<String> jsonResponse) {
        jsonResponse.setRetCode("401");
        return jsonResponse;
    }

    public String errorStr(JsonResponse<String> jsonResponse) {
        return com.alibaba.fastjson.JSONObject.toJSONString(jsonResponse);
    }

    //处理请求之后
//    @Override
//    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
//        LOGGER.info("处理请求之后:---------postHandle");
//    }
//
    //响应后
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
        //LOGGER.info("响应后:---------afterCompletion");
        LoginUtil.remove();
    }


}
