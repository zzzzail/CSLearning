package com.java.dsa.leetcode;

/**
 * @link https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-ii/
 * 买卖股票的最佳时机II
 */
public class P122BestTimeToBuyAndSellStockII1 {
    
    public static void main(String[] args) {
        P122BestTimeToBuyAndSellStockII1 solution = new P122BestTimeToBuyAndSellStockII1();
        int[] prices1 = {7, 1, 5, 3, 6, 4};
        int res1 = solution.maxProfit2(prices1);
        System.out.println(res1);
    }
    
    /**
     * 贪心算法
     * 一直买，一直卖，要卖出赚到钱那就累积下来。
     */
    public int maxProfit(int[] prices) {
        int profit = 0;
        for (int i = 1; i < prices.length; ++i) {
            profit += Math.max(0, prices[i] - prices[i - 1]);
        }
        return profit;
    }
    
    /**
     * 第一步：定义状态
     * dp[i][j] 表示到下标为 i 的这一天，持股状态为 j 时，我们手上拥有的最大现金数。
     * * 第一维 i 表示下标为 i 的那一天（ 具有前缀性质，即考虑了之前天数的交易 ）；
     * * 第二维 j 表示下标为 i 的那一天是持有股票，还是持有现金。
     * * 这里 0 表示持有现金（cash），1 表示持有股票（stock）。
     * j = 0 是不持股，前一天没有交易、前一天把股票卖出
     * j = 1 是持股，今天购买股票、前一天有持股，当天没有交易
     * <p>
     * 第二步：状态转移
     * * 状态从持有现金（cash）开始，到最后一天我们关心的状态依然是持有现金（cash）；
     * * 每一天状态可以转移，也可以不动。
     * * 由于不限制交易次数，除了最后一天，每一天的状态可能不变化，也可能转移；
     * * 写代码的时候，可以不用对最后一天单独处理，输出最后一天，状态为 0 的时候的值即可。
     * <p>
     * 第三步：确定初始值
     * * 如果什么都不做，dp[0][0] = 0；
     * * 如果持有股票，当前拥有的现金数是当天股价的相反数，即 dp[0][1] = -prices[i]；
     * <p>
     * 第四步：确认是输出值
     * 输出 dp[len - 1][0]，因为一定有 dp[len - 1][0] > dp[len - 1][1]。
     */
    public int maxProfit2(int[] prices) {
        int n = prices.length;
        if (n == 1) return 0;
        
        int[][] dp = new int[n][2];
        dp[0][0] = 0;
        dp[0][1] = -prices[0];
        for (int i = 1; i < n; ++i) {
            /*
             这两个操作是互斥的
             要么买股票，从昨天持有现金的数目减去今天股票的价格。
             要么卖股票，以今天的价格卖出昨天持有的股票数
             就像滚雪球一样一直累积
             */
            // 持有现金（要么不动，要么把手里的股票卖了）
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
            // 持有股票（要么不卖股票，要么今天买了股票）
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i]);
        }
        return dp[n - 1][0];
    }
    
    /**
     * 也可以将买卖交换一下
     */
    public int maxProfit3(int[] prices) {
        int n = prices.length;
        int[][] dp = new int[n][2];
        dp[0][0] = -prices[0];  // 买股票
        dp[0][1] = 0;           // 卖出后手里的现金数
        for (int i = 1; i < n; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] - prices[i]);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] + prices[i]);
        }
        return dp[n - 1][1];
    }
    
}
