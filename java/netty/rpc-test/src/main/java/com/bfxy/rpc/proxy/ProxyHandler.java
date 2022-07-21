package com.bfxy.rpc.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyHandler implements InvocationHandler {
    
    private Object target;
    
    
    public Object bind(Object target, Class[] ineterfaces) {
        this.target = target;
        return (Subject) Proxy.newProxyInstance(
                target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),
                this);
    }
    
    
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //1 在转调具体目标对象之前，可以执行一些功能处理
        System.err.println("before");
        //2 转调具体目标对象的方法
        Object result = method.invoke(target, args);
        
        //3 在转调具体目标对象之后，可以执行一些功能处理
        try {
            Thread.sleep(1000);
            System.err.println("after");
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        return result;
    }
    
}
