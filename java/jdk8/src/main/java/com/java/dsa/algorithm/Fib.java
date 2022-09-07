package com.java.dsa.algorithm;

import java.util.HashMap;
import java.util.Map;

public class Fib {
    
    public static void main(String[] args) {
        /*
        fib(0) = 0
        fib(1) = 1
        fib(2) = 1
        fib(3) = 2
        fib(4) = 3
        fib(5) = 5
        fib(6) = 8
         */
        System.out.println(fib(10));
        System.out.println(fib2(10));
        System.out.println(fib3(10));
    }
    
    /**
     * 这是最垃圾的 斐波那契 数算法！
     */
    public static int fib(int n) {
        // return n <= 1 ? n : fib(n - 1) + fib(n - 2);
        if (n <= 1) return n;
        return fib(n - 1) + fib(n - 2);
    }
    
    private static Map<Integer, Integer> cache = new HashMap<>();
    /**
     * 使用缓存的方法
     */
    public static int fib2(int n) {
        if (n <= 1) return n;
        int n_1, n_2;
        if (cache.containsKey(n - 1)) n_1 = cache.get(n - 1);
        else {
            n_1 = fib2(n - 1);
            cache.put(n - 1, n_1);
        }
        if (cache.containsKey(n - 2)) n_2 = cache.get(n - 2);
        else {
            n_2 = fib2(n - 2);
            cache.put(n - 2, n_2);
        }
        return n_1 + n_2;
    }
    
    /**
     * 使用动态规划的方式
     */
    public static int fib3(int n) {
        if (n <= 1) return n;
        int a = 0, b = 1, c = a + b;
        while (n > 1) {
            c = a + b;
            a = b;
            b = c;
            --n;
        }
        return c;
    }
}
