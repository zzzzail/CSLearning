package com.java.dsa.leetcode;

/**
 * @link https://leetcode-cn.com/problems/climbing-stairs/
 * 爬楼梯问题
 */
public class P70ClimbingStairs5 {
    
    /**
     * recursion
     * 超时
     */
    public int climbStairs(int n) {
        if (n <= 3) return n;
        return climbStairs(n - 1) + climbStairs(n - 2);
    }
    
    /**
     * dp
     */
    public int climbStairs2(int n) {
        if (n <= 3) return n;
        int a = 2, b = 3, c = a + b;
        while (n > 3) {
            c = a + b;
            a = b;
            b = c;
            --n;
        }
        return c;
    }
}
