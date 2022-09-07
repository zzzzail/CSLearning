package com.java.designpattern.singleton;

/**
 * @author zail
 * @date 2022/5/26
 */
public class Singleton02 {
    
    private static Singleton02 instance;
    
    private Singleton02() {
    }
    
    public static Singleton02 getInstance() {
        if (instance == null) {
            instance = new Singleton02();
        }
        return instance;
    }
}
