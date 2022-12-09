package alg.leetcode;

import java.util.Arrays;

public class P322CoinChange {
    
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
    public static int coinChange3(int[] coins, int amount) {
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
