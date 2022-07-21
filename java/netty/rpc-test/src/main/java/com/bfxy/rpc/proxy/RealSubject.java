package com.bfxy.rpc.proxy;

public class RealSubject implements Subject {
    
    @Override
    public void doSomething() {
        System.out.println("call doSomething()");
    }
    
}
