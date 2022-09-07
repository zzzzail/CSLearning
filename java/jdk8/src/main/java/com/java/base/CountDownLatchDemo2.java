package com.java.base;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

/**
 * @author zail
 * @date 2022/6/21
 */
public class CountDownLatchDemo2 {
    
    public static void main(String[] args) {
        // 初始值 8 有八个人需要出寝室门
        CountDownLatch countDownLatch = new CountDownLatch(8);
        for (int i = 1; i <= 8; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "出去啦");
                try {
                    Thread.sleep(3000);
                }
                catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                // 出去一个人计数器就减 1
                countDownLatch.countDown();
            }, String.valueOf(i)).start();
        }
        
        try {
            countDownLatch.await(); // 阻塞等待计数器归零
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 阻塞的操作 ： 计数器  num++
        System.out.println(Thread.currentThread().getName() + "====寝室人都已经出来了，关门向教室冲！！！====");
    }
}
