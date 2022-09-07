package com.java.designpattern.base;

/**
 * @author zhangxq
 * @date 2022/8/12
 */
public class Client {
    
    public static void main(String[] args) {
        
        API api = API.defaultImpl();
        api.doSomething();
    
        API api2 = API.customImpl();
        api2.doSomething();
        
    }
}
