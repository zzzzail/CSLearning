package alg.leetcode;

/**
 * @link https://leetcode-cn.com/problems/maximal-square/
 * 最大正方形
 */
public class P221MaximalSquare1 {
    
    public static void main(String[] args) {
    
    }
    
    /**
     * 子问题： dp[i][j] 是以 matrix[i - 1][j - 1] 为右下角的正方形的最大边长，在动态规划的过程中
     * 计算出最大边长即可。
     * 若此时格子值为 1，则以此为右下角的正方形的最大边长为：上面的正方形、左面的正方形或左上的正方形中，最小
     * 的那个，在加上此格。
     * DP 数组定义：
     * DP 方程：
     * 初始化：
     * 结果：
     */
    public int maximalSquare(char[][] matrix) {
        if (matrix == null || matrix.length < 1 || matrix[0].length < 1) return 0;
        
        int height = matrix.length;
        int width = matrix[0].length;
        int maxSide = 0;
        int[][] dp = new int[height + 1][width + 1];
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                if (matrix[row][col] == '1') {
                    dp[row + 1][col + 1] = Math.min(Math.min(dp[row + 1][col], dp[row][col + 1]), dp[row][col]) + 1;
                    maxSide = Math.max(maxSide, dp[row + 1][col + 1]);
                }
            }
        }
        return maxSide * maxSide;
    }
}
