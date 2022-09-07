package com.java.dsa.leetcode;

/**
 * @link https://leetcode-cn.com/problems/delete-and-earn/
 * 删除并获得点数
 */
public class P740DeleteAndEarn {
    
    public static void main(String[] args) {
    
    }
    
    public int deleteAndEarn(int[] nums) {
        int[] sum = new int[10005];
        int[] val = new int[10005];
        for (int i = 0; i < nums.length; ++i) {
            ++sum[nums[i]];
        }
        for (int i = 0; i < val.length; ++i) {
            val[i] = i * sum[i];
        }
    
        // 以下是打家劫舍的代码
        int n = val.length;
        int[] dp = new int[n];
        dp[0] = val[0];
        dp[1] = Math.max(val[0], val[1]);
        for (int i = 2; i < n; ++i) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + val[i]);
        }
        return dp[n - 1];
    }
}
