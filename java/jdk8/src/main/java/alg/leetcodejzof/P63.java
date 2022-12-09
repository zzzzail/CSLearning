package alg.leetcodejzof;

/**
 * 股票的最大利润
 *
 * @author zail
 * @link https://leetcode.cn/problems/gu-piao-de-zui-da-li-run-lcof/
 * @date 2022/7/18
 */
public class P63 {
    
    public static void main(String[] args) {
    
    }
    
    public int maxProfit(int[] prices) {
        int cost = Integer.MAX_VALUE, profit = 0;
        for (int price : prices) {
            cost = Math.min(cost, price);
            profit = Math.max(profit, price - cost);
        }
        return profit;
    }
    
    /**
     * dp 子问题：要想求出第 n 天的最大利润就要求出，第 n - 1 天的最大利润和今天卖出的最大利润，取两个值的最大者即可。
     * dp 数组定义： dp[0][i] 表示第 i 天买入所花费钱数
     * dp[1][i] 表示第 i 天可以获得的最大利润
     * dp 递推公式： dp[0][i] = max{ dp[0][i - 1], -prices[i] }
     * dp[1][i] = max{ dp[1][i - 1], dp[0][i - 1] + prices[i] }
     * dp 数组初始化：dp[0][0] = -prices[0]
     * dp[1][0] = 0
     * 结果：dp[1][n - 1]
     */
    public int maxProfit2(int[] prices) {
        if (prices == null || prices.length == 0 || prices.length == 1) return 0;
        
        int n = prices.length;
        int[][] dp = new int[2][n];
        dp[0][0] = -prices[0];
        dp[1][0] = 0;
        for (int i = 1; i < n; i++) {
            dp[0][i] = Math.max(dp[0][i - 1], -prices[i]);
            dp[1][i] = Math.max(dp[1][i - 1], dp[0][i - 1] + prices[i]);
        }
        return dp[1][n - 1];
    }
}
