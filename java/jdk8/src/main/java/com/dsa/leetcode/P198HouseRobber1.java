package com.dsa.leetcode;

public class P198HouseRobber1 {
    
    public static void main(String[] args) {
        P198HouseRobber1 solution = new P198HouseRobber1();
        int[] nums1 = new int[]{2, 7, 9, 3, 1};
        System.out.println(solution.rob(nums1));
    
        int[] nums2 = {0};
        System.out.println(solution.rob(nums2));
    }
    
    /**
     * 动态规划
     * 注意： 不能偷相邻的房屋，即偷第 i 个房屋就不能偷第 i - 1 个房屋
     * 子问题：偷 i - 2 房屋和 i 房屋的和，偷 i - 1 房屋的最大值
     * DP 数组定义： dp[i] 表示偷盗当前 i 的时刻所能偷到的最大值
     * DP 方程： dp[i] += max(nums[i] + dp[i - 2], dp[i - 1])
     * 初始化： dp[0] = nums[0], dp[1] = max(nums[0], nums[1])
     * 结果： dp[n - 1]
     */
    public int rob(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int n = nums.length;
        if (n == 1) return nums[0];
        
        int[] dp = new int[n];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
        for (int i = 2; i < n; i++) {
            dp[i] += Math.max(nums[i] + dp[i - 2], dp[i - 1]);
        }
        return dp[n - 1];
    }
    
}
