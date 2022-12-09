package alg.leetcode;

/**
 * @link https://leetcode-cn.com/problems/coin-change-2/
 * 零钱兑换 II
 */
public class P518CoinChangeII1 {
    
    public static void main(String[] args) {
        /*
               0  1  2  3  4  5
            0  0  0  0  0  0  0
            1  0  1  1  1  1  1
            2  0  1  2  2  3  3
            5  0  1  2  2  3  4
         */
    }
    
    /**
     * 动态规划
     * 子问题： 要想求出 amount 金额的可以凑成的组合数就要求出 amount - 1 金额的可以凑成的组合数。
     * DP 数组定义： dp[i][j] i 表示使用从 0 ～ i 个面额的硬币，j 表示需要兑换的货币金额，
     * dp[i][j] 表示可以兑换的组合数。
     * DP 方程： 如果 coin > amt 则 dp[i][amt] = dp[i - 1][amt]
     * 否则 dp[i][amt] = dp[i][amt - coin] + dp[i - 1][amt]
     * 初始化： dp[0 ~ m][0] = 1 每一种货币兑换 0 元面额的钱数都有 1 中兑换方式
     * 结果： dp[m - 1][n - 1]
     */
    public static int change(int amount, int[] coins) {
        int m = coins.length + 1;
        int n = amount + 1;
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) dp[i][0] = 1;
        for (int i = 1; i < m; i++) {
            int coin = coins[i - 1];
            for (int amt = 0; amt < n; amt++) {
                if (coin > amt) dp[i][amt] = dp[i - 1][amt];
                else dp[i][amt] = dp[i][amt - coin] + dp[i - 1][amt];
            }
        }
        return dp[m - 1][n - 1];
    }
}
