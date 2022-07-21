package com.base;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author Zail
 * @version 1.0
 * @date 2022/5/22
 */
public class CallableDemo {
    
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        MyThread<String> myThread = new MyThread<>();
        FutureTask<String> futureTask = new FutureTask<>(myThread);
    
        // 创建一个新的线程，将 futureTask 交给这个线程执行
        Thread cur = new Thread(futureTask);
        cur.start();
    
        String res1 = futureTask.get();
        System.out.println(res1);
    }
    
    static class MyThread<T> implements Callable<T> {
        @Override
        public T call() throws Exception {
            System.out.println(Thread.currentThread().getName() + " is running");
            return (T) "123";
        }
    }
}
