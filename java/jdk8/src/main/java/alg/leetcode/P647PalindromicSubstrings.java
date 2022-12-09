package alg.leetcode;

/**
 * @link https://leetcode-cn.com/problems/palindromic-substrings/
 * 回文子串
 */
public class P647PalindromicSubstrings {
    
    public static void main(String[] args) {
    
    }
    
    /**
     */
    public int countSubstrings(String s) {
        int count = 0;
        int n = s.length();
        
        boolean[][] dp = new boolean[n][n];
        for (int j = 0; j < n; j++) {
            for (int i = 0; i <= j; i++) {
                if (i == j) { // 单个字符的情况
                    dp[i][j] = true;
                    count++;
                }
                else if (j - i == 1 && s.charAt(i) == s.charAt(j)) { // 两个字符的情况
                    dp[i][j] = true;
                    count++;
                }
                // 多于两个字符
                else if (j - i > 1 && s.charAt(i) == s.charAt(j) && dp[i + 1][j - 1]) {
                    dp[i][j] = true;
                    count++;
                }
            }
        }
        return count;
    }
    
}
