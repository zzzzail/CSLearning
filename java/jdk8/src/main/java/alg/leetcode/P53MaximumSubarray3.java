package alg.leetcode;

public class P53MaximumSubarray3 {
    
    public static void main(String[] args) {
    
    }
    
    /**
     * 1. 暴力枚举
     * 优化：正数开始正数结束
     * 2. DP
     * 子问题：max_sum(i) = max( max_sum(i - 1), nums[i] )
     * 状态数组定义： f[i] 达到第 i 个元素的时候，连续数组的最大值
     * DP 方程： f[i] = max( f[i - 1], nums[i] )
     * 初始： f[0] = nums[0]
     * 结果： 计算 f[i] 后产生的最大值
     */
    public static int maxSubArray(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int n = nums.length;
        int[] dp = new int[n];
        dp[0] = nums[0];
        int maxValue = nums[0];
        for (int i = 1; i < n; ++i) {
            // dp[i] = max( dp[i - 1], nums[i] );
            dp[i] = Math.max(dp[i - 1], 0) + nums[i];
            maxValue = Math.max(maxValue, dp[i]);
        }
        return maxValue;
    }
}
