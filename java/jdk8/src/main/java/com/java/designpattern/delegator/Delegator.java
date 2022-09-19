package com.java.designpattern.delegator;

/**
 * @author zhangxq
 * @since 2022/9/19
 */
public interface Delegator<T> {
    
    /**
     * 获取被代理的对象
     *
     * @return
     */
    T getDelegate();
    
    /**
     * 增强方法
     */
    public void enhanceFun();
    
}
