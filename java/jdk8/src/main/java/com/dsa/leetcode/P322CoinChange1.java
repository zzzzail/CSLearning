package com.dsa.leetcode;

import java.util.Arrays;

public class P322CoinChange1 {
    
    public static void main(String[] args) {
        int[] coins1 = new int[]{1, 2, 5};
        int amount1 = 11;
        int res1 = coinChange(coins1, amount1);
        System.out.println(res1);
        
        int res2 = coinChange(new int[]{2}, 3);
        System.out.println(res2);
        
        int res3 = coinChange(new int[]{1}, 0);
        System.out.println(res3);
    }
    
    /**
     * 贪心算法
     */
    public static int coinChange(int[] coins, int amount) {
        Arrays.sort(coins);
        int res = 0;
        int remainder = amount;
        for (int i = coins.length - 1; i >= 0 && remainder > 0; --i) {
            while (remainder >= coins[i]) {
                remainder -= coins[i];
                ++res;
            }
        }
        return remainder == 0 ? res : -1;
    }
    
    /**
     * 动态规划
     */
    public static int coinChange2(int[] coins, int amount) {
        int max = amount + 1; // 不可找零的情况
        int m = coins.length + 1, n = amount + 1;
        int[][] dp = new int[m][n];
        Arrays.fill(dp[0], max);
        for (int row = 1; row < m; ++row) {
            int coin = coins[row - 1]; // 某个零钱
            for (int col = 1; col < n; ++col) {
                /*
                如果找不开零，直接取上一行的值即可（因为上一行是没有这 coin 这个面额的硬币所能得到的解）
                如果可以找开，则取两者的之间小的值
                    没有 coin 这个面额的硬币找零所使用的硬币个数（上一行的值）；
                    使用 1 张 col 面值的硬币后，还剩 row - col 元需要找零的情况，两者相加的值；
                 */
                if (coin > col) dp[row][col] = dp[row - 1][col];
                else dp[row][col] = Math.min(dp[row - 1][col], dp[row][col - coin] + 1);
            }
        }
        return dp[m - 1][n - 1] == max ? -1 : dp[m - 1][n - 1];
    }
}
