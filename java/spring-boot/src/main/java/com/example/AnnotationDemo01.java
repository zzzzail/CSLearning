package com.example;

import com.example.service.AppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * @author zail
 * @date 2022/6/24
 */
@Component
public class AnnotationDemo01 {
    
    @Autowired
    @Qualifier("appServiceImpl")
    private AppService appService;
    // @Resource
    // private AppService appService2;
    
    public void beanNameService() {
        String appServiceBeanName = appService.getBeanName();
        System.out.println("appServiceBeanName = " + appServiceBeanName);
    }
    
}
