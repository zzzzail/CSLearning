package com.java.dsa.leetcode;

/**
 * @link https://leetcode.cn/problems/maximal-rectangle/
 * 最大矩形
 */
public class P85MaximalRectangle1 {
    
    public static void main(String[] args) {
    
    }
    
    /**
     * 动态规划
     * 子问题：计算 row 行 col 列为 1 时矩形的面积，并记录面积的最大值即可
     * DP 数组定义： dp[row][col] 为 row 行 col 列高度为 1 的矩形的面积，即从前到后加起来即可。
     * DP 方程： matrix[row][col] = 1 时 dp[row][col] = dp[row][col - 1] + 1
     */
    public int maximalRectangle(char[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        if (m == 1 && n == 1) return matrix[0][0] - '0';
        
        // 保存以当前数字结尾的连续 1 的个数
        int[][] dp = new int[m][n];
        int maxArea = 0;
        for (int row = 0; row < m; ++row) {
            for (int col = 0; col < n; ++col) {
                // 遇到 0 啥也不是
                if (matrix[row][col] == '0') continue;
                
                // 更新一行的宽度，即假设高低为 1，面积 = width * 1
                // dp[row][col] = dp[row][col - 1] + 1
                dp[row][col] = (col > 0 ? dp[row][col - 1] : 0) + 1;
    
                // 记录所有行中最小的数
                int minWidth = dp[row][col];
                // 优化：要是没有之前的面积大何必继续上试呢？（此优化可以从 15ms 减少到 5 ms）
                if (maxArea > 0 && minWidth * (row + 1) <= maxArea) continue;
                // 向上扩展行
                for (int rowUp = row; rowUp >= 0; --rowUp) {
                    // 优化 1： 向上试探时遇到 0 则快速结束
                    if (dp[rowUp][col] == 0) break;
                    int height = row - rowUp + 1;
                    // 找最小的数作为矩阵的宽
                    minWidth = Math.min(minWidth, dp[rowUp][col]);
                    // 更新找到的最大面积
                    maxArea = Math.max(maxArea, minWidth * height);
                }
            }
        }
        return maxArea;
    }
    
    public int maximalRectangle2(char[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        int[][] dp = new int[m][n];
        for (int i = 0; i < n; i++) {
            if (matrix[0][i] == '1') dp[0][i] = 1;
        }
        for (int i = 1; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // 记录高度
                if (matrix[i][j] == '1') dp[i][j] = dp[i - 1][j] + 1;
            }
        }
        int ans = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (dp[i][j] != 0) {
                    // 优化
                    if (dp[i][j] * n <= ans) continue;
                    
                    int cnt = 1;
                    for (int k = j + 1; k < n; k++) {
                        if (dp[i][k] < dp[i][j]) break;
                        cnt++;
                    }
                    for (int k = j - 1; k >= 0; k--) {
                        if (dp[i][k] < dp[i][j]) break;
                        cnt++;
                    }
                    ans = Math.max(ans, cnt * dp[i][j]);
                }
            }
        }
        return ans;
    }
}
