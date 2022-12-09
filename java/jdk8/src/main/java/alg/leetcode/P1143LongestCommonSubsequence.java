package alg.leetcode;

/**
 * @link https://leetcode-cn.com/problems/longest-common-subsequence/
 * 最长公共子序列
 */
public class P1143LongestCommonSubsequence {
    
    public static void main(String[] args) {
        int res1 = longestCommonSubsequence("abcde", "ace");
        System.out.println(res1);
        
        int res2 = longestCommonSubsequence("psnw", "vozsh");
        System.out.println(res2);
    }
    
    /**
     * 动态规划
     * <p>
     * DP 方程：
     * 当 text1[i - 1] == text2[j - 1];
     * dp[i][j] = dp[i − 1][j − 1] + 1;
     * 否则 dp[i][j] = max(dp[i - 1][j], dp[i][j - 1])
     */
    public static int longestCommonSubsequence(String text1, String text2) {
        int m = text1.length(), n = text2.length();
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 1; i <= m; ++i) {
            for (int j = 1; j <= n; ++j) {
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                }
                else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[m][n];
    }
    
}
