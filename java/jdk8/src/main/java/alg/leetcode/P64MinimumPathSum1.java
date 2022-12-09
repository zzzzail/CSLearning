package alg.leetcode;

/**
 * @link https://leetcode-cn.com/problems/minimum-path-sum/
 * 最小路径和
 */
public class P64MinimumPathSum1 {
    
    public static void main(String[] args) {
        int[][] grid1 = new int[][]{{1, 3, 1}, {1, 5, 1}, {4, 2, 1}};
        int res1 = minPathSum(grid1);
        System.out.println(res1);
    }
    
    /**
     * 从左上角到右下角的路径，所经过的方块之和最小。（每次只能向下或向前移动）
     * DP 子问题： 移动到 row, col 位置上的最小值就要知道移动到 row - 1, col
     * 和 row, col - 1 位置上的最小值
     * DP 数组定义： dp[row][col] 表示移动到 row 行 col 列的最小路径和
     * DP 方程： dp[row][col] = grid[row][col] + min(dp[row - 1][col], dp[row][col - 1])
     * 初始化： 第一行挨个相加，第一列挨个相加
     * 结果：dp[m - 1][n - 1]
     */
    public static int minPathSum(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        if (m == 1 && n == 1) return grid[0][0];
        
        int[][] dp = new int[m][n];
        dp[0][0] = grid[0][0];
        for (int i = 1; i < m; i++) dp[i][0] = dp[i - 1][0] + grid[i][0];
        for (int i = 1; i < n; i++) dp[0][i] = dp[0][i - 1] + grid[0][i];
        
        for (int row = 1; row < m; ++row) {
            for (int col = 1; col < n; ++col) {
                dp[row][col] = grid[row][col] + Math.min(dp[row - 1][col], dp[row][col - 1]);
            }
        }
        
        return dp[m - 1][n - 1];
    }
    
}
