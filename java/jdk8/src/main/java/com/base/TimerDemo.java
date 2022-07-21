package com.base;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class TimerDemo {
    
    public static void main(String[] args) {
        /*
        计时器
        Timer 内部类 TaskQueue 用于存放定时任务，它是一个基于最小堆实现的优先级队列 Balanced binary heap。
        基于 TimerTask 的 nextExecutionTime 排序。
        获取最短时间内需要执行的任务 TimerTask 的时间复杂度是 O(1) 也就是常数级 Constant time performance。
         */
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                System.out.println("Future Task 1 is running.");
                System.out.println("当前时间: " + new Date() +
                        " 线程名称: " + Thread.currentThread().getName());
            }
        };
        System.out.println("当前时间: " + new Date() +
                " 线程名称: " + Thread.currentThread().getName());
        timer.schedule(task, 1000);
    }
}
