package alg.leetcode;

public class P62UniquePaths {
    
    public static void main(String[] args) {
        System.out.println(uniquePaths(3, 2));
        System.out.println(uniquePaths(3, 7));
        System.out.println(uniquePaths2(3, 7));
        System.out.println(uniquePaths4(3, 7));
    }
    
    /**
     * 傻递归实现
     * O(2^n)
     */
    public static int uniquePaths(int m, int n) {
        return path(m, n, 0, 0);
    }
    
    public static int path(int m, int n, int row, int col) {
        if (row == m - 1 && col == n - 1) return 1;
        if (!inArea(m, n, row, col)) return 0;
        return path(m, n, row + 1, col) + path(m, n, row, col + 1);
    }
    
    private static boolean inArea(int m, int n, int row, int col) {
        return 0 <= row && row < m && 0 <= col && col < n;
    }
    
    /**
     * 使用动态规划实现
     * 构建矩阵 + 动态规划是最快的办法！
     */
    public static int uniquePaths2(int m, int n) {
        int[][] grid = new int[m][n];
        grid[m - 1][n - 1] = 1;
        for (int row = m - 1; row >= 0; --row) {
            for (int col = n - 1; col >= 0; --col) {
                int down = inArea(m, n, row + 1, col) ? grid[row + 1][col] : 0;
                int right = inArea(m, n, row, col + 1) ? grid[row][col + 1] : 0;
                grid[row][col] += down + right;
            }
        }
        return grid[0][0];
    }
    
    /**
     * 使用组合数学的方法
     */
    public static int uniquePaths3(int m, int n) {
        long ans = 1;
        for (int x = n, y = 1; y < m; ++x, ++y) {
            ans = ans * x / y;
        }
        return (int) ans;
    }
    
    /**
     * 动态规划
     * 子问题： 要想知道到达 row, col 为止可以走的不同路径，就要知道到达 row - 1, col 和
     * 到达 row, col - 1 位置的不同路径数量，两者相加即可算出到达 row, col 可以走的不同路径。
     * DP 数组定义： dp[row][col] row 表示行， col 表示列
     * DP 方程： dp[row][col] = dp[row - 1][col] + dp[row][col - 1]
     * 初始化： dp[row][0 ~ col - 1] = 1 横着走一直是 1
     * dp[0 ~ row - 1][0] = 1 竖着走也一直是 1
     * 结果： dp[m - 1][n - 1]
     */
    public static int uniquePaths4(int m, int n) {
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; ++i) dp[i][0] = 1;
        for (int i = 0; i < n; ++i) dp[0][i] = 1;
        for (int row = 1; row < m; ++row) {
            for (int col = 1; col < n; ++col) {
                dp[row][col] = dp[row - 1][col] + dp[row][col - 1];
            }
        }
        return dp[m - 1][n - 1];
    }
}
