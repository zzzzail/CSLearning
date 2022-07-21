package com.base;

public class ConcurrentDemo {
    
    public static void main(String[] args) {
    
    }
    
    // 使用 synchronized 修饰的方法
    public synchronized void fun() {
        
        // 使用 synchronized 修饰的变量
        synchronized (this) {
            System.out.println("a");
        }
        Object obj = new Object();
        synchronized (obj) {
        
        }
    }
    
}
