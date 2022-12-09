package alg.leetcode;

public class P85MaximalRectangle2 {
    
    public static void main(String[] args) {
    
    }
    
    public int maximalRectangle(char[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        if (m == 1 && n == 1) return matrix[0][0] - '0';
        
        int maxVal = 0;
        int[][] dp = new int[m][n];
        for (int row = 0; row < m; row++) {
            for (int col = 0; col < n; col++) {
                if (matrix[row][col] == '0') continue;
                // 记录行的宽
                dp[row][col] = (col > 0 ? dp[row][col - 1] : 0) + 1;
                // 依次查找有没有高度大于 1 且面积较大的矩形
                int minWidth = dp[row][col];
                // 优化2： 要是没有之前的面积大何必继续上试呢？
                if (maxVal != 0 && minWidth * (row + 1) <= maxVal) continue;
                for (int rowUp = row; rowUp >= 0; --rowUp) {
                    if (dp[rowUp][col] == 0) break; // 优化1：向上遇到 0 则快速结束
                    int height = row - rowUp + 1;
                    minWidth = Math.min(minWidth, dp[rowUp][col]);
                    maxVal = Math.max(maxVal, minWidth * height);
                }
            }
        }
        return maxVal;
    }
}
