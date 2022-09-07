package com.java.dsa.leetcode;

import java.util.Arrays;

/**
 * @link https://leetcode-cn.com/problems/replace-elements-with-greatest-element-on-right-side/
 * 将每个元素替换为右侧最大元素
 */
public class P1299ReplaceElementsWithGreatestElementOnRightSide1 {
    
    public static void main(String[] args) {
        int[] arr1 = new int[]{17, 18, 5, 4, 6, 1};
        int[] res1 = replaceElements(arr1);
        System.out.println(Arrays.toString(res1));
    }
    
    /**
     * dp[i] 为 [i + 1, n - 1] 范围内的最大值
     * 初始状态：
     * dp[n - 1] = -1
     * dp[n - 2] = dp[n - 1]
     * 状态转移方程：
     * dp[i] = max(dp[i + 1], nums[i + 1])
     * 确定结果： dp
     */
    public static int[] replaceElements(int[] arr) {
        if (arr == null || arr.length == 0) return new int[0];
        if (arr.length == 1) return new int[]{-1};
        
        int n = arr.length;
        int[] dp = new int[n];
        dp[n - 1] = -1;
        dp[n - 2] = arr[n - 1];
        for (int i = n - 3; i >= 0; --i) {
            dp[i] = Math.max(dp[i + 1], arr[i + 1]);
        }
        return dp;
    }
}
