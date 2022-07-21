package com.designpattern.singleton;

/**
 * 饿汉式
 *
 * @author zail
 * @date 2022/5/26
 */
public class Singleton01 {
    
    private static Singleton01 instance = new Singleton01();
    
    private Singleton01() {
    }
    
    public static Singleton01 getInstance() {
        return instance;
    }
    
}
