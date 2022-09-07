package com.java.dsa.leetcode;

public class P63UniquePathsII2 {
    
    public static void main(String[] args) {
        P63UniquePathsII2 solution = new P63UniquePathsII2();
        int[][] grid1 = {{0, 1, 0}, {0, 1, 0}, {0, 0, 0}};
        int res1 = solution.uniquePathsWithObstacles(grid1);
        System.out.println(res1);
    }
    
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        if (obstacleGrid[0][0] != 0) return 0;
        
        int m = obstacleGrid.length, n = obstacleGrid[0].length;
        int[][] dp = new int[m + 1][n + 1];
        dp[0][1] = 1;
        for (int row = 1; row <= m; row++) {
            for (int col = 1; col <= n; col++) {
                if (obstacleGrid[row - 1][col - 1] == 0) {
                    dp[row][col] = dp[row - 1][col] + dp[row][col - 1];
                }
            }
        }
        return dp[m][n];
    }
}
