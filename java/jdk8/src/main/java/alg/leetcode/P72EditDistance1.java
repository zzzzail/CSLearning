package alg.leetcode;

/**
 * @link https://leetcode-cn.com/problems/edit-distance/
 * 编辑距离
 */
public class P72EditDistance1 {
    
    public static void main(String[] args) {
        P72EditDistance1 solution = new P72EditDistance1();
        int res1 = solution.minDistance("horse", "ros");
        System.out.println(res1);
    }
    
    /**
     * 动态规划（二维 DP）
     * 子问题： 要想求解 w1[i] 到 w2[j] 的编辑距离就要求 w1[i - 1] 到 w2[j - 1] 的编辑距离
     * DP 数组定义： dp[i][j]
     * i 表示 word1 的字母为止，j 表示 word 2 的字母为止。
     * i 的定义域为 [0, word1.length], j 的定义域为 [0, word2.length]
     * DP 方程：
     * 如果 word1[i] == word2[j] 则 dp[i][j] = dp[i - 1][j - 1] （无需任何变化直接可以编辑过去）
     * 否则 dp[i][j] = min(dp[i - 1][j - 1], dp[i - 1][j], dp[i][j - 1]) + 1
     * dp[i - 1][j - 1] 表示替换操作
     * dp[i - 1][j] 表示删除操作
     * dp[i][j - 1] 表示插入操作
     * 初始化： dp[0 ~ m - 1][0] = i, dp[0][0 ~ n - 1] = j
     * 结果： dp[m - 1][n - 1]
     */
    public int minDistance(String word1, String word2) {
        int m = word1.length() + 1, n = word2.length() + 1;
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) dp[i][0] = i;
        for (int j = 0; j < n; j++) dp[0][j] = j;
        
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                }
                else {
                    dp[i][j] = Math.min(dp[i - 1][j - 1], Math.min(dp[i - 1][j], dp[i][j - 1])) + 1;
                }
            }
        }
        return dp[m - 1][n - 1];
    }
    
    /**
     * 子问题：要想求 word1[0...i] 到 word2[0...j] 的最小编辑距离，就要求出 word1[0...i - 1] 到 word2[0...j- 1] 的
     * 最小编辑距离，然后在根据 word1[i] 和 word2[j] 字符是否相等计算 word1[0...i] 到 word2[0...j] 的最小编辑距离。
     * DP 数组定义： dp[i][j] 表示 word1[0...i] 到 word2[0...j] 的最小编辑距离
     * DP 方程：
     * | if word1[i] = word2[j] then dp[i][j] = dp[i - 1][j - 1]
     * | else
     * |       dp[i][j] = min(
     * |                         dp[i - 1][j - 1],
     * |                         dp[i - 1][j    ],
     * |                         dp[i    ][j - 1]) + 1
     * |
     * | 其中   dp[i - 1][j - 1]   表示替换操作
     * |       dp[i - 1][j]       表示 word1 回退一格，即删除操作
     * |       dp[i][j - 1]       表示 word2 回退一格，即将 word1[i] 这个字符插入，插入操作
     * 初始化： 注意要多申请一行、一列
     * dp[0][i] = i
     * dp[i][0] = i
     * 结果：dp[m][n]
     */
    public int minDistance2(String word1, String word2) {
        int m = word1.length(), n = word2.length();
        if (m == 0) return n;
        if (n == 0) return m;
        
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            dp[i][0] = i;
        }
        for (int i = 1; i <= n; i++) {
            dp[0][i] = i;
        }
        
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                }
                else {
                    dp[i][j] = min(dp[i - 1][j - 1], dp[i - 1][j], dp[i][j - 1]) + 1;
                }
            }
        }
        return dp[m][n];
    }
    
    private int min(int n1, int n2, int n3) {
        return Math.min(Math.min(n1, n2), n3);
    }
    
}
