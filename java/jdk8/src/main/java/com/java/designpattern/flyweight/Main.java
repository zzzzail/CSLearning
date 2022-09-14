package com.java.designpattern.flyweight;

/**
 * @author zhangxq
 * @since 2022/9/14
 */
public class Main {
    
    public static void main(String[] args) {
        FlyweightFactory factory = new FlyweightFactory();
        Flyweight flyweight = factory.getFlyweight("F1");
        
        flyweight.operation("opt1");
    
        flyweight = factory.getFlyweight("F1");
        flyweight.operation("opt2");
    }
}
