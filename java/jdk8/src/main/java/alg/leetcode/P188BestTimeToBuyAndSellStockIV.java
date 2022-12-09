package alg.leetcode;

/**
 * @link https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-iv/
 * 买卖股票的最佳时机 IV
 */
public class P188BestTimeToBuyAndSellStockIV {
    
    public static void main(String[] args) {
    
    }
    
    public static int maxProfit(int k, int[] prices) {
        int n = prices.length;
        if (k == 0 || n < 2) return 0;
        
        // 状态转移方程里下标有 -1 的时候，为了防止数组下标越界，多开一行，因此第一维的长度是 len + 1
        // 第二维表示交易次数，从 0 开始，因此第二维的长度是 k + 1
        // 第三维表示是否持股，0：不持股，1：持股
        int[][][] dp = new int[n + 1][k + 1][2];
        // 初始化：把持股的部分都设置为一个较小的负值
        // 注意：如果使用默认值 0，状态转移的过程中会做出错误的决策
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= k; j++) {
                dp[i][j][1] = Integer.MIN_VALUE;
            }
        }
        // 注意：i 和 j 都有 1 个位置的偏移
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= k; j++) { // 二次循环的 dp 问题
                dp[i][j][1] = Math.max(dp[i - 1][j][1], dp[i - 1][j - 1][0] - prices[i - 1]);
                dp[i][j][0] = Math.max(dp[i - 1][j][0], dp[i - 1][j][1] + prices[i - 1]);
            }
        }
        return dp[n][k][0];
    }
}
