package com.designpattern.proxy.cglibproxy;

/**
 * 被 CGLIB 动态代理的对象
 *
 * @author zail
 * @date 2022/5/25
 */
public class AliSmsSender {
    
    public void send() {
        System.out.println("send message.");
    }
}
