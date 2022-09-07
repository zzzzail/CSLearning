package com.java.base;

import java.util.Date;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduledThreadPoolExecutorDemo {
    
    public static void main(String[] args) throws InterruptedException {
        TimerTask repeatedTask = new TimerTask() {
            @Override
            public void run() {
                System.out.println("当前时间: " + new Date() +
                        " 线程名称: " + Thread.currentThread().getName());
            }
        };
        /*
        ScheduledThreadPoolExecutor 线程池任务执行器。
        内部使用 DelayQueue 作为任务队列。
         */
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(3);
        long delay = 1000L;
        long period = 1000L;
        // 每隔 period 时间就执行一次
        executor.scheduleAtFixedRate(repeatedTask, delay, period, TimeUnit.MILLISECONDS);
        Thread.sleep(delay + period * 5);
        executor.shutdown();
    }
}
