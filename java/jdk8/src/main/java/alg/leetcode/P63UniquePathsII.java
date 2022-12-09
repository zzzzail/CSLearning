package alg.leetcode;

public class P63UniquePathsII {
    
    public static void main(String[] args) {
        P63UniquePathsII solution = new P63UniquePathsII();
        int[][] obstacleGrid1 = new int[][]{{0, 0}, {1, 1}, {0, 0}};
        int res1 = solution.uniquePathsWithObstacles(obstacleGrid1);
        System.out.println(res1);
    }
    
    /**
     * 动态规划
     * 子问题： 要想知道到达 row, col 为止可以走的不同路径，就要知道到达 row - 1, col 和
     * 到达 row, col - 1 位置的不同路径数量，两者相加即可算出到达 row, col 可以走的不同路径。
     * 当遇到障碍物的时候，该格子值为 0 无法到达。
     * DP 数组定义： dp[row][col]
     * DP 方程： obstacleGrid[row][col] = 0 时
     * dp[row][col] = dp[row - 1][col] + dp[row][col - 1]，否则 dp[row][col] = 0
     * 初始化： dp[0][0 ~ n - 1] = 1, dp[0 ~ m - 1][0] = 1
     * 结果： dp[m - 1][n - 1]
     */
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        if (obstacleGrid == null || obstacleGrid.length == 0) return 0;
        if (obstacleGrid[0][0] != 0) return 0;
        
        int m = obstacleGrid.length, n = obstacleGrid[0].length;
        int[][] dp = new int[m][n];
        for (int i = 0; i < m && obstacleGrid[i][0] == 0; ++i) dp[i][0] = 1;
        for (int i = 0; i < n && obstacleGrid[0][i] == 0; ++i) dp[0][i] = 1;
        for (int i = 1; i < m; ++i) {
            for (int j = 1; j < n; ++j) {
                dp[i][j] = obstacleGrid[i][j] == 0 ? dp[i - 1][j] + dp[i][j - 1] : 0;
            }
        }
        return dp[m - 1][n - 1];
    }
    
    /**
     * 动态规划
     * DP 子问题：要想知道有走到 row,col 位置上有多少种不同的路径，就要知道 row - 1,col 和 row,col - 1
     * DP 数组：dp[i][j] 表示走到 i 行 j 列有多少种不同的路径
     * DP 方程：dp[i][j] = dp[i - 1][j] + dp[i][j - 1]   (obstacleGrid[i][j] == 0)
     * 初始化： dp[0][0] = 1
     * 结果： dp[m][n]
     */
    public int uniquePathsWithObstacles2(int[][] obstacleGrid) {
        if (obstacleGrid[0][0] != 0) return 0;
        
        int m = obstacleGrid.length, n = obstacleGrid[0].length;
        int[][] dp = new int[m + 1][n + 1];
        dp[0][1] = 1; // 从这个位置开始出发
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
