package com.bfxy.netty.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bfxy.common.protobuf.UserModule;
import com.bfxy.netty.client.Client;

@RestController
public class IndexController {
    
    
    @RequestMapping("/index")
    public String index() {
        return "index";
    }
    
    @RequestMapping("/save")
    public String save() {
        UserModule.User user = UserModule.User.newBuilder()
                .setUserId("001")
                .setUserName("张三")
                .build();
        
        Client
                .getInstance()
                .sendMessage("user", "save", user);
        
        return "save";
    }
    
    @RequestMapping("/update")
    public String update() {
        UserModule.User user = UserModule.User.newBuilder()
                .setUserId("002")
                .setUserName("李四")
                .build();
        
        Client
                .getInstance()
                .sendMessage("user", "update", user);
        
        return "update";
    }
    
}
