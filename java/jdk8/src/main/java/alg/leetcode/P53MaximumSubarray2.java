package alg.leetcode;

public class P53MaximumSubarray2 {
    
    public static void main(String[] args) {
    
    }
    
    public static int min(int[] nums) {
        if (nums == null || nums.length == 0)return 0;
        int n = nums.length;
        int[] dp = new int[n];
        dp[0] = nums[0];
        int maxValue = nums[0];
        for (int i = 1; i < n; i++) {
            dp[i] = Math.max(dp[i - 1] + nums[i], nums[i]);
            maxValue = Math.max(maxValue, dp[i]);
        }
        return maxValue;
    }
}
