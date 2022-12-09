package com.java.demo;

import java.time.Duration;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

/**
 * @author zhangxq
 * @since 2022/9/26
 */
public class Demo01 {
    
    public static void main(String[] args) {
        System.out.println("Hello Java 19!");
        
        try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
            for (int i = 0; i < 10_0000; i++) {
                int finalI = i;
                executor.submit(() -> {
                    System.out.println(finalI);
                });
            }
        }
    }
}
