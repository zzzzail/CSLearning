package demo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author Zail
 * @version 1.0
 * @date 2022/5/25
 */
public class ScheduledThreadPoolDemo {
    
    public static void main(String[] args) {
        ScheduledExecutorService es = Executors.newScheduledThreadPool(3);
        // 延迟 100s 后执行
        es.schedule(new Thread(), 100, TimeUnit.SECONDS);
        // 按照固定频率执行的
        // 第一次是 initialDelay 时间执行，第二次是 initialDelay + period 时间执行，第三次是
        // initialDelay + 2 * period 时间执行
        es.scheduleAtFixedRate(
                new Thread(),
                100,
                100,
                TimeUnit.SECONDS
        );
        // 固定一个延迟时间执行
        es.scheduleWithFixedDelay(
                new Thread(),         // 作业
                100,        // 初始延迟时间
                100,            // 延迟时间
                TimeUnit.SECONDS      // 时间单位
        );
        
    }
}
