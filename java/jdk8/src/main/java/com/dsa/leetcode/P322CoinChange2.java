package com.dsa.leetcode;

import java.util.Arrays;

/**
 *
 */
public class P322CoinChange2 {
    
    public static void main(String[] args) {
        int[] coins1 = new int[]{2};
        int amount1 = 3;
        int res1 = coinChange(coins1, amount1);
        System.out.println(res1);
    }
    
    /**
     * 子问题： 要兑换 a 先要判断当前面额的硬币 c 和 a 的大小
     * 若 c > a 则可以兑换： 1 + dp[c][a - c]; 就相当于兑换一张 c 面额的硬币，再兑换余下的数额的货币
     * 若 c < a，则不能兑换： 和 c 这个面额的硬币的兑换结果一样
     * 状态数组定义： dp[i][j]  i 表示用第几个面额的硬币；j 表示兑换多少金额
     * DP 方程：
     * 当 j >= coins[i] 时 dp[i][j] = min( dp[i - 1][j], 1 + dp[i][j - coins[i]] )
     * 否则 dp[i][j] = dp[i - 1][j]
     * 初始值： dp[0][1 ~ amount] = INFINITY 不可兑换
     * dp[1 ~ coins][0] = 0 兑换金额为 0 时只兑换 0 个面额的硬币即可
     * 结果： dp[m - 1][amount]
     */
    public static int coinChange(int[] coins, int amount) {
        int max = amount + 1; // 用 max 表示兑换不了
        int m = coins.length + 1, n = amount + 1;
        int[][] dp = new int[m][n];
        Arrays.fill(dp[0], 1, amount + 1, max);
        for (int i = 1; i < m; ++i) {
            int coin = coins[i - 1];
            for (int amt = 1; amt < n; ++amt) {
                if (amt >= coin) dp[i][amt] = Math.min(dp[i - 1][amt], dp[i][amt - coin] + 1);
                else dp[i][amt] = dp[i - 1][amt];
            }
        }
        return dp[m - 1][amount] == max ? -1 : dp[m - 1][amount];
    }
}
