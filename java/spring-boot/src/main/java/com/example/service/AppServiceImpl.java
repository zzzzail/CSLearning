package com.example.service;

import org.springframework.beans.factory.BeanNameAware;
import org.springframework.stereotype.Service;

/**
 * @author zail
 * @date 2022/6/25
 */
@Service
public class AppServiceImpl implements AppService, BeanNameAware {
    
    private String beanName;
    
    @Override
    public void setBeanName(String name) {
        System.out.println("调用 BeanNameAware 接口，并设置 beanName。");
        this.beanName = name;
    }
    
    @Override
    public String getBeanName() {
        return this.beanName;
    }
}
