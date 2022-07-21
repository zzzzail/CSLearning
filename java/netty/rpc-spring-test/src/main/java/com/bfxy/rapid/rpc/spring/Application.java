package com.bfxy.rapid.rpc.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

import com.bfxy.rapid.rpc.spring.annotation.RapidComponentScan;

@Configuration
@RapidComponentScan(basePackages= {"com.bfxy.rapid.rpc.spring"})
@SpringBootApplication
public class Application {
	
	public static void main(String[] args) {
        SpringApplication application = new SpringApplication(Application.class);
        application.run(args);
	}
}