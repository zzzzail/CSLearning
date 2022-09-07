package com.java.base;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author Zail
 * @version 1.0
 * @date 2022/5/22
 */
public class ThreadDemo {
    
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        MyThread myThread = new MyThread();
        myThread.start();
        
        // Thread.sleep(2000);
        
        // 可以使用 interrupt 中断线程
        // myThread.interrupt();
        
        // sleep 会一直占用锁
        // Thread.sleep(10);
        // wait 方法会释放掉锁
        // myThread.wait();
    
        // 使用 Callable 的方式创建线程并执行
        Callable<String> callable = () -> {
            return Thread.currentThread().getName() + "：xxx";
        };
        FutureTask<String> task = new FutureTask<>(callable);
        Thread thread1 = new Thread(task);
        thread1.start();
        String res1 = task.get();
        System.out.println(res1);
        
        // 一直睡下去
        Thread.sleep(Long.MAX_VALUE);
    }
    
    static class MyThread extends Thread {
        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + " is running");
        }
    }
}
