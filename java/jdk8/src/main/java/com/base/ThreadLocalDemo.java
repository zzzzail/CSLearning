package com.base;

/**
 * @author zail
 * @date 2022/5/27
 */
public class ThreadLocalDemo {
    private static final ThreadLocal<String> threadLocal = new ThreadLocal<>();
    
    public static void main(String[] args) {
        
        new Thread(() -> {
            try {
                threadLocal.set("aaa");
                Thread.sleep(500);
                System.out.println("threadA:" + threadLocal.get());
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        
        new Thread(() -> {
            threadLocal.set("bbb");
            System.out.println("threadB:" + threadLocal.get());
        }).start();
    }
}
