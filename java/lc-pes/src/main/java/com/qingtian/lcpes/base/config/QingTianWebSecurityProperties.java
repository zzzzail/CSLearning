package com.qingtian.lcpes.base.config;

import com.qingtian.lcpes.base.config.properties.*;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author zail
 * @date 2022/1/24
 */
@Data
@ConfigurationProperties(prefix = "qingtian.web.security")
@Component
public class QingTianWebSecurityProperties {

    // 是否开启
    private boolean enable = true;

    // CSRF 配置
    private CsrfProperties csrf = new CsrfProperties();

    // CORS 配置
    private CorsProperties cors = new CorsProperties();

    // 需要身份认证的匹配链接
    private String[] authenticatedUrls = {"/**", "/api/**"};

    // 不需要身份认证的匹配链接
    private String[] unauthenticatedUrls = {"/", "/index","/login" ,"/api/login","/authentication/require", "/logout", "/access/denied"};

    // 提示需要登录页面
    private String requireAuthenticationUrl = "/authentication/require";

    // 登录页面
    private String loginPage = "/login";

    // 处理登录的链接
    private String loginProcessingUrl = "/login";

    // 退出登录 url
    private String logoutUrl = "/logout";

    // 登录成功跳转的 url
    private String loginSuccessUrl = "/index";

    // 访问拒绝页面
    private String accessDeniedUrl = "/access/denied";

    // 验证码配置
    private ValidationCodeProperties validationCode = new ValidationCodeProperties();

    // 记住我
    private String rememberMeName = "rememberMe";

    // 记住我时间配置(单位秒)
    private Integer rememberMeSeconds = 3600 * 2;

    /* 表单登陆 */
    private FormLoginProperties formLogin;

    // jwt 配置
    private JWTProperties jwt = new JWTProperties();

}
