package alg.leetcode;

/**
 * @link https://leetcode-cn.com/problems/house-robber-ii/
 * 打家劫舍 II
 */
public class P213HouseRobberII1 {
    
    public static void main(String[] args) {
        int[] nums = new int[] {1,2,4,4};
        System.out.println(rob(nums));
    }
    
    /**
     * 如何才能保证第一间房屋和最后一间房屋不同时偷窃呢？如果偷窃了第一间房屋，则不
     * 能偷窃最后一间房屋，因此偷窃房屋的范围是第一间房屋到倒数第二间房屋；如果偷窃
     * 了最后一间房屋，则不能偷窃第一间房屋，因此偷窃房屋的范围是第二间房屋到最后一间房屋。
     */
    public static int rob(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int n = nums.length;
        if (n == 1) return nums[0];
        if (n == 2) return Math.max(nums[0], nums[1]);
        return Math.max(
                rob(nums, 0, n - 2),
                rob(nums, 1, n - 1)
        );
    }
    
    public static int rob(int[] nums, int start, int end) {
        int n = end - start + 1;
        int[] dp = new int[n];
        dp[0] = nums[start];
        dp[1] = Math.max(nums[start], nums[start + 1]);
        for (int i = 2; i < n; ++i) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[start + i]);
        }
        return dp[n - 1];
    }
    
}
