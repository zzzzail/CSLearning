package demo;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @author Zail
 * @version 1.0
 * @date 2022/5/24
 */
public class ABADemo {
    
    public static void main(String[] args) {
        AtomicReference<Integer> atomicInteger = new AtomicReference<>(100);
        AtomicStampedReference<Integer> atomicStampedInteger = new AtomicStampedReference<>(100, 0);
        new Thread(() -> {
            // ABA
            atomicInteger.compareAndSet(100, 101);
            atomicInteger.compareAndSet(101, 100);
        }, "thread-1").start();
        
        new Thread(() -> {
            try {
                // 保证线程1完成一次ABA操作
                TimeUnit.SECONDS.sleep(1);
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
            boolean flag = atomicInteger.compareAndSet(100, 2000);
            System.out.println("atomicInteger " + (flag ? "更新成功" : "更新失败") + "，值：" + atomicInteger.get());
        }, "thread-22").start();
        
        new Thread(() -> {
            // ABA
            int stamp = atomicStampedInteger.getStamp();
            atomicStampedInteger.compareAndSet(100, 101, stamp, stamp + 1);
            stamp = atomicStampedInteger.getStamp();
            atomicStampedInteger.compareAndSet(101, 100, stamp, stamp + 1);
        }, "thread-3").start();
        
        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
            int stamp = 0;
            boolean flag = atomicStampedInteger.compareAndSet(100, 2000, stamp, stamp + 1);
            System.out.println("atomicStampedInteger " + (flag ? "更新成功" : "更新失败") + "，值：" + atomicStampedInteger.getReference());
        }, "thread-4").start();
    }
    
    
}
