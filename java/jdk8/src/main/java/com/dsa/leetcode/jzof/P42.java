package com.dsa.leetcode.jzof;

/**
 * 连续子数组的最大和
 *
 * @author zail
 * @link https://leetcode.cn/problems/lian-xu-zi-shu-zu-de-zui-da-he-lcof/
 * @date 2022/7/18
 */
public class P42 {
    
    public static void main(String[] args) {
        P42 solution = new P42();
        
        int[] nums1 = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        int res1 = solution.maxSubArray(nums1);
        System.out.println(res1);
    }
    
    /**
     * 动态规划
     * dp 子问题：要想求出第 i 个位置以前并包含第 i 个位置数组的最大和，就要求出第 i - 1 位置的最大和，并且
     * 根据第 i - 1 的值判断是否有帮助，若是非零正数则对第 i 位置数组的最大和有帮助，可以加上该值。若没有帮助
     * 则可直接赋值为 nums[i]。
     * dp 数组定义：dp[i] 表示 nums[0 ~ i] 数组的最大和
     * dp 数组初始化： dp[0] = nums[0]
     * dp 递推公式：  dp[i] = dp[i - 1] + nums[i], dp[i - 1] > 0
     * dp[i] = nums[i], dp[i - 1] <= 0
     * 结果：期间记录的 max 值
     */
    public int maxSubArray(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        
        int n = nums.length;
        int[] dp = new int[n];
        dp[0] = nums[0];
        int max = dp[0];
        for (int i = 1; i < n; i++) {
            if (dp[i - 1] > 0) dp[i] = dp[i - 1] + nums[i];
            else dp[i] = nums[i];
            max = Math.max(max, dp[i]);
        }
        return max;
    }
}
