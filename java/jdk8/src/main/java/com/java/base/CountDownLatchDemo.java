package com.java.base;

import java.util.concurrent.CountDownLatch;

/**
 * @author Zail
 * @version 1.0
 * @date 2022/5/23
 */
public class CountDownLatchDemo {
    
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch c = new CountDownLatch(3);
        WaitThread waitThread1 = new WaitThread("wait-thread-1", c);
        WaitThread waitThread2 = new WaitThread("wait-thread-2", c);
    
        WorkThread workThread1 = new WorkThread("work-thread-1", c);
        WorkThread workThread2 = new WorkThread("work-thread-2", c);
        WorkThread workThread3 = new WorkThread("work-thread-3", c);
        
        waitThread1.start();
        waitThread2.start();
        Thread.sleep(3000);
        workThread1.start();
        workThread2.start();
        workThread3.start();
    }
    
    static class WaitThread extends Thread {
        private String name;
        private CountDownLatch countDownLatch;
        
        public WaitThread(String name, CountDownLatch countDownLatch) {
            this.name = name;
            this.countDownLatch = countDownLatch;
        }
        
        @Override
        public void run() {
            System.out.println(name + " 等待中");
            try {
                countDownLatch.await();
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(name + " 等待完成");
        }
    }
    
    static class WorkThread extends Thread {
        private String name;
        private CountDownLatch countDownLatch;
    
        public WorkThread(String name, CountDownLatch countDownLatch) {
            this.name = name;
            this.countDownLatch = countDownLatch;
        }
    
        @Override
        public void run() {
            System.out.println(name + " 开始执行");
            try {
                Thread.sleep(2000);
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(name + " 执行完成");
            // 倒计时一次，标记执行完成
            countDownLatch.countDown();
        }
    }
}
