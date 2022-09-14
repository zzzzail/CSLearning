package com.qingtian.lcpes.base.config;

import cn.hutool.core.thread.ExecutorBuilder;
import cn.hutool.core.thread.ThreadFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;

@Configuration
public class ThreadPoolConfig {

    @Bean
    public ExecutorService receiveExecutorService() {
        return ExecutorBuilder.create()
                .setCorePoolSize(10)
                .setMaxPoolSize(30)
                .setWorkQueue(new LinkedBlockingQueue<>(100))
                .setKeepAliveTime(0)
                .setHandler(new ThreadPoolExecutor.DiscardOldestPolicy())
                .setThreadFactory(ThreadFactoryBuilder.create().setNamePrefix("msg-receive-thread-").build())
                .build();
    }

    @Bean
    public ExecutorService sendExecutorService() {
        return ExecutorBuilder.create()
                .setCorePoolSize(10)
                .setMaxPoolSize(30)
                .setWorkQueue(new LinkedBlockingQueue<>(100))
                .setKeepAliveTime(0)
                .setHandler(new ThreadPoolExecutor.DiscardOldestPolicy())
                .setThreadFactory(ThreadFactoryBuilder.create().setNamePrefix("msg-send-thread-").build())
                .build();
    }
}
