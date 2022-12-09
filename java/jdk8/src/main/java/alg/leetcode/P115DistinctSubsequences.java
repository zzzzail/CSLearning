package alg.leetcode;

/**
 * @link https://leetcode.cn/problems/distinct-subsequences/
 * 不同的子序列
 */
public class P115DistinctSubsequences {
    
    public static void main(String[] args) {
        P115DistinctSubsequences solution = new P115DistinctSubsequences();
        String s1 = "rabbbit", t1 = "rabbit";
        int res1 = solution.numDistinct2(s1, t1);
        System.out.println(res1);
    }
    
    /**
     * @link https://leetcode.cn/problems/distinct-subsequences/solution/dong-tai-gui-hua-by-powcai-5/
     * DP 子问题：
     * DP 数组定义： dp[i][j] 表示 T 前 i 个字符可以由 S 的前 j 个字符串组成最多个数
     * DP 方程:
     * 当 S[j] == T[i] , dp[i][j] = dp[i - 1][j - 1] + dp[i][j - 1] （选择 s[j] 为当前转换的字母和
     * 不选择 s[j] 为当前转换的字母两者之和）
     * 当 S[j] != T[i] , dp[i][j] = dp[i][j - 1] （不相等，就只能不选择 s[j] 为当前转换的字母了）
     * 初始化： dp[0][0...n - 1] = 1
     * 结果： dp[m][n]
     */
    public int numDistinct(String s, String t) {
        int m = t.length(), n = s.length();
        int[][] dp = new int[m + 1][n + 1];
        for (int j = 0; j <= n; ++j) dp[0][j] = 1;
        
        for (int i = 1; i <= m; ++i) {
            for (int j = 1; j <= n; ++j) {
                if (t.charAt(i - 1) == s.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + dp[i][j - 1];
                }
                else {
                    dp[i][j] = dp[i][j - 1];
                }
            }
        }
        return dp[m][n];
    }
    
    /**
     * 子问题： 要求 s[0...i] 字符串中 t[0...j] 出现的个数，就要求出 s[0...i - 1] 字符串
     * 中 t[0...j - 1] 出现的个数。
     * DP 数组定义： dp[i][j] 表示 s[0...i] 字符串中 t[0...j] 出现的个数
     * DP 方程： if s[i] == t[j]: dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j]
     * （选择 s[j] 为当前转换的字母、不选择 s[j] 为当前转换的字母两者之和）
     * 否则 dp[i][j] = dp[i - 1][j]
     * （不相等，就只能不选择 s[j] 为当前转换的字母了）
     * 初始化： dp[0...m - 1][0] = 1 当 s 为空串时 s 的子序列中 t 出现的个数为 1
     * 结果： dp[m][n]
     */
    public int numDistinct2(String s, String t) {
        int m = s.length(), n = t.length();
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 0; i < m; i++) {
            dp[i][0] = 1;
        }
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                // 优化 ？？？
                // 只考虑矩阵下三角的值即可
                if (j > i) break;
                if (s.charAt(i - 1) == t.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j];
                }
                else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        return dp[m][n];
    }
}
