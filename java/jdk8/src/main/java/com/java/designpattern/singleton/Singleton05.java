package com.java.designpattern.singleton;

/**
 * 使用枚举的方式实现单例模式。
 * <p>
 * 简洁，且提供序列化的机制，并由 JVM 从根本上提供保障，绝对防止多次实例化。是更加
 * 简洁、安全和高效的实现单例的方式。
 *
 * @author zail
 * @date 2022/5/26
 */
public enum Singleton05 {
    
    UNIQUE_INSTANCE,
    
    ;
    
    public void singletonOperation() {
        System.out.println("这里可以实现一些单例的操作");
    }
}
