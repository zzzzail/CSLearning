package com.qingtian.lcpes.base.file;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

@Configuration
@Slf4j
public class AsyncExportFileConfiguration {

    /** 核心线程数(默认线程数) */
    private int corePoolSize = 1;
    /** 最大线程数 */
    private int maxPoolSize = 10;
    /** 允许线程空闲时间（单位：默认为秒） */
    private int keepAliveTime = 60;
    /** 缓冲队列大小（设置线程池中阻塞队列的大小，默认 Integer.MAX_VALUE） */
    private int queueCapacity = 100;
    /* 线程的前缀名（方便定位问题） */
    private String threadNamePrefix = "async-exporter-";

    @Bean
    public Executor asyncExportFileExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        // 配置核心线程数
        executor.setCorePoolSize(corePoolSize);
        // 配置最大线程数
        executor.setMaxPoolSize(maxPoolSize);
        // 配置空闲时间
        executor.setKeepAliveSeconds(keepAliveTime);
        // 配置队列大小
        executor.setQueueCapacity(queueCapacity);
        // 配置线程前缀名
        executor.setThreadNamePrefix(threadNamePrefix);

        // rejection-policy：当pool已经达到 max size 的时候，如何处理新任务
        // CALLER_RUNS：不在新线程中执行任务，而是有调用者所在的线程来执行
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        // 执行初始化
        executor.initialize();
        return executor;
    }
}
