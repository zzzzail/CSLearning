package com.java.dsa.leetcode;

public class P70ClimbingStairs6 {
    
    public int climbStairs(int n) {
        if (n <= 2) return n;
        return climbStairs(n - 1) + climbStairs(n - 2);
    }
    
    /**
     * 动态规划
     * 子问题： 要求上到第 n 个台阶有多少种方法，就要求出 n - 1 和 n - 2
     * DP 数组定义： dp[i] 为上到第 i 个台阶有多少种方法
     * DP 方程： dp[i] = dp[i - 1] + dp[i - 2]
     * 初始化： dp[0 ~ 2] = i
     * 结果： dp[n]
     */
    public int climbStairs2(int n) {
        if (n <= 2) return n;
        
        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i <= n; ++i) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }
    
}
