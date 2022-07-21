package com.designpattern.singleton;

/**
 * 使用 volatile + synchronized 的方式保证其线程安全
 *
 * @author zail
 * @date 2022/5/26
 */
public class Singleton03 {
    
    // 1. 加入 volatile 关键字保证其可见性
    private static volatile Singleton03 instance;
    
    private Singleton03() {
    }
    
    public static Singleton03 getInstance() {
        if (instance == null) {
            // 2. 对类加锁之后再实例化对象
            synchronized (Singleton03.class) {
                // 3. 获取锁之后再次判断对象是否存在（此时可能已经先被其他占有锁的线程初始化好了）
                if (instance == null) {
                    instance = new Singleton03();
                }
            }
        }
        return instance;
    }
}
