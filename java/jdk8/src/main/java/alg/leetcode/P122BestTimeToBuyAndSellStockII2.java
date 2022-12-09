package alg.leetcode;

public class P122BestTimeToBuyAndSellStockII2 {
    
    public static void main(String[] args) {
    
    }
    
    /**
     * 返回你能获得的最大利润
     * 只要赚钱就卖掉，卖了之后又买，再找机会卖掉
     * 子问题：当天买入或卖出取决于前一天的价格和今天的价格，今天的价格比昨天高则可获益，所以选择卖出。
     * 今天价格比昨天低，所以选择买入。
     * DP 数组定义： dp[i][j] 表示第 i 天 j 状态下手里的钱数
     * j = 0 表示持股 j = 1 表示不持股 j = 2 表示累计卖出获得的收益
     * DP 方程： dp[i][0] = max(dp[i][0], -prices[i])
     * dp[i][1] = dp[i - 1][0] + prices[i]
     */
    public int maxProfit(int[] prices) {
        int n = prices.length;
        int[][] dp = new int[n][2];
        return 0;
    }
    
}
