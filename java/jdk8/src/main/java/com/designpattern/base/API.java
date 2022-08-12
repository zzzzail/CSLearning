package com.designpattern.base;

/**
 * @author zhangxq
 * @date 2022/8/12
 */
@FunctionalInterface
public interface API {
    
    /**
     * 接口方法
     */
    void doSomething();
    
    /**
     * 一个默认实现
     *
     * @return
     */
    static API defaultImpl() {
        return new APIImpl();
    }
    
    static API customImpl() {
        return new CustomAPIImpl();
    }
}
