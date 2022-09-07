package com.java.base;

import java.util.concurrent.locks.StampedLock;

/**
 * @author Zail
 * @version 1.0
 * @date 2022/5/24
 */
public class StampedLockDemo {
    
    private final StampedLock lock = new StampedLock();
    
    public static void main(String[] args) {
    }
    
    public void mutate() {
        long stamp = lock.writeLock();
        try {
            System.out.println("write");
        }
        finally {
            // 按照 stamp 解锁
            lock.unlockWrite(stamp);
        }
    }
    
    public String access() {
        // Optimistic 乐观的
        // 乐观的尝试读
        long stamp = lock.tryOptimisticRead();
        String data = null;
        if (!lock.validate(stamp)) {
            stamp = lock.readLock();
            try {
                data = "read something";
            }
            finally {
                lock.unlockRead(stamp);
            }
        }
        return data;
    }
    
    
    // 官方的实例
    static class Point {
        private double x, y;
        private final StampedLock sl = new StampedLock();
        
        void move(double deltaX, double deltaY) { // an exclusively locked method
            long stamp = sl.writeLock();
            try {
                x += deltaX;
                y += deltaY;
            } finally {
                sl.unlockWrite(stamp);
            }
        }
        
        double distanceFromOrigin() { // A read-only method
            long stamp = sl.tryOptimisticRead();
            double currentX = x, currentY = y;
            if (!sl.validate(stamp)) {
                stamp = sl.readLock();
                try {
                    currentX = x;
                    currentY = y;
                } finally {
                    sl.unlockRead(stamp);
                }
            }
            return Math.sqrt(currentX * currentX + currentY * currentY);
        }
        
        void moveIfAtOrigin(double newX, double newY) { // upgrade
            // Could instead start with optimistic, not read mode
            long stamp = sl.readLock();
            try {
                while (x == 0.0 && y == 0.0) {
                    long ws = sl.tryConvertToWriteLock(stamp);
                    if (ws != 0L) {
                        stamp = ws;
                        x = newX;
                        y = newY;
                        break;
                    }
                    else {
                        sl.unlockRead(stamp);
                        stamp = sl.writeLock();
                    }
                }
            } finally {
                sl.unlock(stamp);
            }
        }
    }
}
