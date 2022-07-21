package com.dsa.leetcode;

/**
 * @link https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-iii/
 * 买卖股票的最佳时机 III
 */
public class P123BestTimeToBuyAndSellStockIII1 {
    
    public static void main(String[] args) {
        int[] prices1 = new int[]{3, 3, 5, 0, 0, 3, 1, 4};
        System.out.println(maxProfit(prices1));
        
    }
    
    /**
     * 动态规划
     * 两点需要注意的：
     * 1. 只能做两笔交易（意思是只能做两次买入和两次卖出）
     * 2. 不能同时参与多笔交易（必须在购买前出售掉之前的股票）
     * <p>
     * 状态定义：dp[i][j][k] 表示在 [0, i] 区间里（状态具有前缀性质），交易进行了 j 次，
     * 并且状态为 k 时我们拥有的现金数。
     * 其中 j 和 k 的含义如下：
     * j = 0 表示没有交易发生；
     * j = 1 表示此时已经发生了 1 次买入股票的行为；
     * j = 2 表示此时已经发生了 2 次买入股票的行为。
     * 即我们 人为规定 记录一次交易产生是在 买入股票 的时候。
     * k = 0 表示当前不持股；
     * k = 1 表示当前持股。
     */
    public static int maxProfit(int[] prices) {
        int n = prices.length;
        if (n <= 1) return 0;
        
        // 第 2 维的 0 没有意义，1 表示交易进行了 1 次，2 表示交易进行了 2 次
        // 为了使得第 2 维的数值 1 和 2 有意义，这里将第 2 维的长度设置为 3
        int[][][] dp = new int[n][3][2];
        // 理解如下初始化
        // 第 3 维规定了必须持股，因此是 -prices[0]
        dp[0][1][1] = -prices[0];
        // 还没发生的交易，持股的时候应该初始化为负无穷
        dp[0][2][1] = Integer.MIN_VALUE;
        for (int i = 1; i < n; i++) {
            // 转移顺序先持股，再卖出
            // 第一次交易，我持股怎么持，（前一天第一次交易的持股和今天买股票）
            dp[i][1][1] = Math.max(dp[i - 1][1][1], -prices[i]);
            // 第一次交易，不持股，（前一天不持股，前一天持股卖出后赚到的钱）
            dp[i][1][0] = Math.max(dp[i - 1][1][0], dp[i - 1][1][1] + prices[i]);
            // 第二次交易，我持股怎么持，（前一天第二次交易的持股和今天买股票）
            dp[i][2][1] = Math.max(dp[i - 1][2][1], dp[i - 1][1][0] - prices[i]);
            // 第二次交易，我持股，（要么继续持，要么卖出）（继续持就是前一天，如果卖出的话就是卖出前一天持股的数）
            dp[i][2][0] = Math.max(dp[i - 1][2][0], dp[i - 1][2][1] + prices[i]);
        }
        // 最后一天不持股的状态都可能成为最大利润。
        return Math.max(dp[n - 1][1][0], dp[n - 1][2][0]);
    }
    
    /**
     * dp[i][j][k] 表示第 i 天的 j 次交易 k 状态下手里的现金数。
     * j = 1, 2
     * k = 0 表示当前不持股
     * k = 1 表示当前持股
     */
    public static int maxProfit2(int[] prices) {
        int n = prices.length;
        int[][][] dp = new int[n][3][2];
        dp[0][1][1] = -prices[0];
        dp[0][2][1] = Integer.MIN_VALUE;
        for (int i = 1; i < n; i++) {
            dp[i][1][1] = Math.max(dp[i - 1][1][1], -prices[i]);
            dp[i][1][0] = Math.max(dp[i - 1][1][0], dp[i - 1][1][1] + prices[i]);
            dp[i][2][1] = Math.max(dp[i - 1][2][1], dp[i - 1][1][0] - prices[i]);
            dp[i][2][0] = Math.max(dp[i - 1][2][0], dp[i - 1][2][1] + prices[i]);
        }
        return Math.max(dp[n - 1][1][0], dp[n - 1][2][0]);
    }
}
