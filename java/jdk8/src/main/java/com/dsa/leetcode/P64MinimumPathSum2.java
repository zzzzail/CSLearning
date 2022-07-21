package com.dsa.leetcode;

public class P64MinimumPathSum2 {
    
    public static void main(String[] args) {
        P64MinimumPathSum2 solution = new P64MinimumPathSum2();
        int[][] grid1 = {{1, 3, 1}, {1, 5, 1}, {4, 2, 1}};
        int res1 = solution.minPathSum(grid1);
        System.out.println(res1);
    }
    
    public int minPathSum(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        if (m == 1 && n == 1) return grid[0][0];
        
        int[][] dp = new int[m][n];
        dp[0][0] = grid[0][0];
        for (int i = 1; i < n; i++) dp[0][i] = grid[0][i] + dp[0][i - 1];
        for (int i = 1; i < m; i++) dp[i][0] = grid[i][0] + dp[i - 1][0];
        for (int row = 1; row < m; ++row) {
            for (int col = 1; col < n; ++col) {
                dp[row][col] = grid[row][col] + Math.min(dp[row - 1][col], dp[row][col - 1]);
            }
        }
        return dp[m - 1][n - 1];
    }
    
}
