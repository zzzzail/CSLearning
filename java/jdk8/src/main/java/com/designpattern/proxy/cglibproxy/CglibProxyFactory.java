package com.designpattern.proxy.cglibproxy;

import net.sf.cglib.proxy.Enhancer;

/**
 * @author zail
 * @date 2022/5/25
 */
public class CglibProxyFactory {
    
    private static final Enhancer enhancer = new Enhancer();

    public static Object getProxy(Object target) {
        enhancer.setSuperclass(target.getClass());
        enhancer.setCallback(new DebugMethodInterceptor());
        return enhancer.create();
    }
    
    public static <T> T getProxy(Class<T> clazz) {
        enhancer.setSuperclass(clazz);
        enhancer.setCallback(new DebugMethodInterceptor());
        return (T) enhancer.create();
    }
}
