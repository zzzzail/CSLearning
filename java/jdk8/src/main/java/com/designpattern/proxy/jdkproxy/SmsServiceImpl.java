package com.designpattern.proxy.jdkproxy;

public class SmsServiceImpl  implements SmsService {
    
    @Override
    public String sendMsg(String msg) {
        System.out.println("send message:" + msg);
        return msg;
    }
}
