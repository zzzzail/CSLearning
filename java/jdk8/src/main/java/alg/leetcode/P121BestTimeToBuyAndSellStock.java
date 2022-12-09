package alg.leetcode;

/**
 * @link https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock/
 * 买卖股票的最佳时机
 */
public class P121BestTimeToBuyAndSellStock {
    
    public static void main(String[] args) {
        P121BestTimeToBuyAndSellStock solution = new P121BestTimeToBuyAndSellStock();
        int[] prices1 = new int[]{7, 1, 5, 3, 6, 4};
        System.out.println(solution.maxProfit(prices1));
        System.out.println(solution.maxProfit2(prices1));
    }
    
    /**
     * 动态规划
     * DP 子问题：当天只能买或卖
     * 买考虑前一天买的价格是否比今天买价格高，如果比今天买价格高则今天买
     * 卖的话，就一直记录卖出的最大值即可
     * DP 数组定义：dp[i][j] 表示第 i 天的时候状态为 j 的值
     * j = 0 表示买，j = 1 表示卖出的最大值
     * dp[i][0] 一直都在找最低点的那一天买入
     * dp[i][1] 一直都在尝试卖出，如果今天卖出比昨天卖出赚的多，那么就记录下来卖出的最大值
     * DP 方程：
     * dp[i][0] = max{dp[i - 1][0], -prices[i]}
     * dp[i][1] = max{dp[i - 1][1], dp[i - 1][0] + prices[i]}
     * 初始化：dp[0][0] = -prices[0]
     * 结果: dp[n - 1][1]
     */
    public int maxProfit(int[] prices) {
        int n = prices.length;
        if (n == 1) return 0;
        
        int[][] dp = new int[n][2];
        dp[0][0] = -prices[0];
        for (int i = 1; i < n; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], -prices[i]);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] + prices[i]);
        }
        return dp[n - 1][1];
    }
    
    /**
     * 降一纬度
     * 执行时间从之前的 30ms 降到了 4ms
     */
    public int maxProfit2(int[] prices) {
        int n = prices.length;
        if (n == 1) return 0;
        
        int[] dp = new int[n];
        dp[0] = -prices[0];
        int maxVal = 0;
        for (int i = 1; i < n; i++) {
            dp[i] = Math.max(dp[i - 1], -prices[i]);
            maxVal = Math.max(maxVal, dp[i - 1] + prices[i]);
        }
        return maxVal;
    }
    
}
