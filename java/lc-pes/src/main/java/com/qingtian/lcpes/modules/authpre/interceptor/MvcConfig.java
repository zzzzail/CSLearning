package com.qingtian.lcpes.modules.authpre.interceptor;

import com.qingtian.lcpes.base.config.QingTianWebSecurityProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
@Slf4j
public class MvcConfig implements WebMvcConfigurer {

    @Autowired
    private AuthpreInterceptor authpreInterceptor;
    @Autowired
    private QingTianWebSecurityProperties qingTianWebSecurityProperties;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //addPathPatterns拦截的路径
        String[] addPathPatterns = {
                "/**"
        };
        //excludePathPatterns排除的路径
        /*String[] excludePathPatterns = {
                "/itmp/authpre/login",
                "/app/driver/**",
                "/base/**",
                "/biz/**"
        };*/
        String[] excludePathPatterns = qingTianWebSecurityProperties.getUnauthenticatedUrls();
        InterceptorRegistration registration = registry.addInterceptor(authpreInterceptor);
        registration.addPathPatterns(addPathPatterns);
        registration.excludePathPatterns(excludePathPatterns);
    }

}
