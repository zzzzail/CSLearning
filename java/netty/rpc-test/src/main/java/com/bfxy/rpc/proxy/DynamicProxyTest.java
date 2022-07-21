package com.bfxy.rpc.proxy;

public class DynamicProxyTest {
    
    public static void main(String[] args) {
        
        RealSubject realSubject = new RealSubject();
        
        ProxyHandler proxyHander = new ProxyHandler();
        
        Subject proxy = (Subject) proxyHander.bind(realSubject, new Class[]{Subject.class});
        
        proxy.doSomething();
    }
    
}
