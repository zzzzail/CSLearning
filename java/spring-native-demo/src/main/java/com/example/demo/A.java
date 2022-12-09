package com.example.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author zhangxq
 * @since 2022/12/9
 */
@Configuration
public class A {
    
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
