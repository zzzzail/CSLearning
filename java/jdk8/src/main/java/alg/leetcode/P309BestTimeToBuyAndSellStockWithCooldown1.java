package alg.leetcode;

/**
 * @link https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-with-cooldown/
 * 买卖股票的最佳时机含冷冻期
 */
public class P309BestTimeToBuyAndSellStockWithCooldown1 {
    
    public static void main(String[] args) {
        P309BestTimeToBuyAndSellStockWithCooldown1 solution = new P309BestTimeToBuyAndSellStockWithCooldown1();
        int[] prices1 = {1, 2, 3, 0, 2};
        System.out.println(solution.maxProfit(prices1));
    }
    
    /**
     * 1. 卖出股票后，你无法在第二天买入股票 (即冷冻期为 1 天)。
     * 2. 不能同时参与多笔交易（必须在再次购买之前抛售掉之前买入的股票）
     * dp[i][j]
     * i 表示第几天
     * j 表示交易
     * 第 0 个参数表示 不持股
     * 第 1 个参数表示 持股
     * 第 2 个参数表示 今天由于卖出了股票的不持股状态
     * <p>
     * j 如何状态转移，如果前一天没有交易，则今天可以交易也可以不交易；如果前一天有交易，则今天不可以交易。
     */
    public int maxProfit(int[] prices) {
        int n = prices.length;
        int[][] dp = new int[n][3];
        dp[0][0] = 0;
        dp[0][1] = -prices[0]; // 第一天买股票
        dp[0][2] = 0;
        // dp[i][0]: 手上不持有股票，并且今天不是由于卖出股票而不持股，我们拥有的现金数
        // dp[i][1]: 手上持有股票时，我们拥有的现金数
        // dp[i][2]: 手上不持有股票，并且今天是由于卖出股票而不持股，我们拥有的现金数
        for (int i = 1; i < n; ++i) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][2]);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i]);
            dp[i][2] = dp[i - 1][1] + prices[i];
        }
        return Math.max(dp[n - 1][0], dp[n - 1][2]);
    }
    
}
