package alg.leetcode;

/**
 * @link https://leetcode.cn/problems/min-cost-climbing-stairs/
 * 使用最小话费爬楼梯
 */
public class P746MinCostClimbingStairs {
    
    public static void main(String[] args) {
        P746MinCostClimbingStairs solution = new P746MinCostClimbingStairs();
        int[] cost1 = {10, 15, 20};
        int res1 = solution.minCostClimbingStairs(cost1);
        System.out.println(res1);
    }
    
    /**
     * DP 子问题：最小花费爬到 i 层，就要从最小花费爬到 i - 1 层和 i - 2 层选择一个较小的值，然后爬到第 i 层
     * DP 数组： dp[i] 表示爬到第 i 层所需的最小花费
     * DP 方程： dp[i] = min(dp[i - 1] + cost[i - 1], dp[i - 2] + cost[i - 2])
     * 初始化： dp[0] = 0, dp[1] = 0。
     * 结果： dp[n]
     * 注意：你可以从下标为 0 的地方开始爬或下标为 1 的开始爬。意思就是说爬到 0、1 层无花费
     */
    public int minCostClimbingStairs(int[] cost) {
        int n = cost.length;
        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 0;
        for (int i = 2; i < n + 1; ++i) {
            dp[i] = Math.min(dp[i - 1] + cost[i - 1], dp[i - 2] + cost[i - 2]);
        }
        return dp[n];
    }
    
}
