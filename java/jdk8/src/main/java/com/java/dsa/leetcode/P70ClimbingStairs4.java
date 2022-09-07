package com.java.dsa.leetcode;

public class P70ClimbingStairs4 {
    
    public static void main(String[] args) {
        P70ClimbingStairs4 solution = new P70ClimbingStairs4();
        int res1 = solution.climbStairs2(5);
        System.out.println(res1);
    }
    
    /**
     * 递归
     */
    public int climbStairs(int n) {
        if (n <= 2) return n;
        return climbStairs(n - 1) + climbStairs(n - 2);
    }
    
    /**
     * 动态规划
     */
    public int climbStairs2(int n) {
        if (n <= 2) return n;
        
        int a = 1, b = 2, c = a + b;
        while (n > 2) {
            c = a + b;
            a = b;
            b = c;
            --n;
        }
        return c;
    }
    
    /**
     * 通项公式
     */
    public int climbStairs3(int n) {
        double sqrt5 = Math.sqrt(5);
        double fibn = Math.pow((1 + sqrt5) / 2, n + 1) - Math.pow((1 - sqrt5) / 2, n + 1);
        return (int) Math.round(fibn / sqrt5);
    }
    
}
