package com.java.base;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author zail
 * @date 2022/7/5
 */
public class ReentrantLockDemo {
    
    public static void main(String[] args) throws InterruptedException {
        ReentrantLock lock = new ReentrantLock(true);
        lock.lock();
        // lock.tryLock(1000, TimeUnit.HOURS);
        lock.unlock();
    }
}
