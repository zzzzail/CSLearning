package com.java.designpattern.flyweight;

/**
 * @author zhangxq
 * @since 2022/9/14
 */
public class ConcreteFlyweight implements Flyweight {
    
    private String intrinsicState;
    
    public ConcreteFlyweight(String intrinsicState) {
        this.intrinsicState = intrinsicState;
    }
    
    @Override
    public void operation(String extrinsicState) {
        // 具体的处理功能，可能会用到享元内部、外部的状态
        System.out.println(extrinsicState);
    }
}
