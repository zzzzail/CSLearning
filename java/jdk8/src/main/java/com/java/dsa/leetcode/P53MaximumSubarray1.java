package com.java.dsa.leetcode;

public class P53MaximumSubarray1 {
    
    public static void main(String[] args) {
    
    }
    
    public static int maxSubArray(int[] nums) {
        int len = nums.length;
        // dp[i] 表示：以 nums[i] 结尾的连续子数组的最大和
        int[] dp = new int[len];
        dp[0] = nums[0];
        for (int i = 1; i < len; i++) {
            if (dp[i - 1] > 0) dp[i] = dp[i - 1] + nums[i];
            else dp[i] = nums[i];
        }
        // 也可以在上面遍历的同时求出 res 的最大值，这里我们为了语义清晰分开写，大家可以自行选择
        int res = dp[0];
        for (int i = 1; i < len; i++) res = Math.max(res, dp[i]);
        return res;
    }
    
    public static int maxSubArray2(int[] nums) {
        int pre = 0;
        int res = nums[0];
        for (int num : nums) {
            pre = Math.max(pre + num, num);
            res = Math.max(res, pre);
        }
        return res;
    }
    
    /**
     * 动态规划
     */
    public static int maxSubArray3(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        dp[0] = nums[0];
        int maxValue = nums[0]; // 记录过程中的最大值
        for (int i = 1; i < n; ++i) {
            dp[i] = Math.max(dp[i - 1] + nums[i], nums[i]);
            maxValue = Math.max(maxValue, dp[i]);
        }
        return maxValue;
    }
    
}
