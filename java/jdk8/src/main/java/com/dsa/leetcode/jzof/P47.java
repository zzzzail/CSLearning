package com.dsa.leetcode.jzof;

/**
 * 礼物的最大价值
 *
 * @author zail
 * @link https://leetcode.cn/problems/li-wu-de-zui-da-jie-zhi-lcof/
 * @date 2022/7/18
 */
public class P47 {
    
    public static void main(String[] args) {
    
    }
    
    /**
     * dp 子问题：第 i, j 位置能拿到的礼物最大价值为，第 i - 1, j 位置的值和第 i, j - 1 位置值两者取大值，并加上 i,j 上的礼物值。
     * dp 数组定义： dp[i][j] 表示从 0, 0 位置出发到 i, j 位置可以拿到礼物的最大值
     * dp 数组初始化： dp[i][0] = dp[i - 1][0] + grid[i][0], 0 <= i < m
     * dp[0][j] = dp[0][j - 1] + grid[0][j], 0 <= j < n
     * dp 递推公式： dp[i][j] = max{ dp[i - 1][j], dp[i][j - 1] } + grid[i][j]
     * 结果：dp[m - 1][n - 1]
     */
    public int maxValue(int[][] grid) {
        if (grid == null) return 0;
        
        int m = grid.length, n = grid[0].length;
        int[][] dp = new int[m][n];
        dp[0][0] = grid[0][0];
        for (int i = 1; i < m; i++) {
            dp[i][0] = dp[i - 1][0] + grid[i][0];
        }
        for (int i = 1; i < n; i++) {
            dp[0][i] = dp[0][i - 1] + grid[0][i];
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
            }
        }
        return dp[m - 1][n - 1];
    }
}
