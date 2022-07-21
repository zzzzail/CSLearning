package com.base;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class DebugInvocationHandler implements InvocationHandler {
    
    /**
     * 代理类中的真实对象
     */
    private Object target;
    
    public DebugInvocationHandler(Object target) {
        this.target = target;
    }
    
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("before method " + method.getName());
        Object result = method.invoke(proxy, args);
        System.out.println("after method " + method.getName());
        return result;
    }
    
}
