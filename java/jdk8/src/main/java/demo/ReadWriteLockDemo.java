package demo;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author Zail
 * @version 1.0
 * @date 2022/5/24
 */
public class ReadWriteLockDemo {
    
    public static void main(String[] args) {
        // 申请一个公平的读写锁
        ReadWriteLock rwl = new ReentrantReadWriteLock(true);
        Lock readLock = rwl.readLock(); // 读锁
        Lock writeLock = rwl.writeLock(); // 写锁
        Worker worker = new Worker(readLock, writeLock);
        
        for (int i = 0; i < 4; i++) {
            new Thread(() -> {
                // 写锁定操作顺序执行的
                worker.set(Thread.currentThread().getName() + " set a nre str " + new Random().nextDouble());
    
                // 读锁定是并发执行的
                try {
                    TimeUnit.SECONDS.sleep(5);
                    String res = worker.get();
                    System.out.println("最后的结果是：" + res);
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
    
    static class Worker {
        private String str;
        private int readCount;
        private final Lock readLock;
        private final Lock writeLock;
        
        public Worker(Lock readLock, Lock writeLock) {
            this.readLock = readLock;
            this.writeLock = writeLock;
        }
        
        public String get() {
            readLock.lock();
            ++readCount;
            System.out.println("读锁定");
            try {
                return str;
            }
            finally {
                readLock.unlock();
                --readCount;
            }
        }
        
        public void set(String str) {
            writeLock.lock();
            System.out.println("写锁定");
            try {
                this.str = str;
            }
            finally {
                writeLock.unlock();
            }
        }
        
        public int getReadCount() {
            return readCount;
        }
    }
    
}
