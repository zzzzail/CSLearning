package alg.leetcode;

/**
 * @link https://leetcode.cn/problems/regular-expression-matching/
 * 正则表达式匹配
 */
public class P10RegularExpressionMatching1 {
    
    public static void main(String[] args) {
    
    }
    
    /**
     * 支持 . 和 * 的正则表达式匹配
     * '.' 匹配任意单个字符
     * '*' 匹配零个或多个前面的那一个元素
     * 子问题：
     * DP 数组： dp[i][j] 表示 s[0...i - 1] 和 p[0...j - 1] 是否匹配
     * DP 方程：
     * |
     * |
     * |
     * |
     * 初始化： 多初始化一行一列
     * 结果： dp[m][n]
     */
    public boolean isMatch(String s, String p) {
        if (s == null || p == null) return false;
        int m = s.length(), n = p.length();
        boolean[][] dp = new boolean[m + 1][n + 1];
        dp[0][0] = true;
        // "" 和 p 的匹配关系初始化，a*a*a*a*a* 这种能够匹配空串，其他的是都是 false。
        //  奇数位不管什么字符都是 false，偶数位为 * 时则: dp[0][i] = dp[0][i - 2]
        for (int i = 2; i <= n; i += 2) {
            if (p.charAt(i - 1) == '*') {
                dp[0][i] = dp[0][i - 2];
            }
        }
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                char sc = s.charAt(i - 1);
                char pc = p.charAt(j - 1);
                if (sc == pc || pc == '.') {
                    dp[i][j] = dp[i - 1][j - 1];
                }
                else if (pc == '*') {
                    if (dp[i][j - 2]) {
                        dp[i][j] = true;
                    }
                    else if (sc == p.charAt(j - 2) || p.charAt(j - 2) == '.') {
                        dp[i][j] = dp[i - 1][j];
                    }
                }
            }
        }
        return dp[m][n];
    }
    
    public boolean isMatch2(String s, String p) {
        char[] cs = s.toCharArray();
        char[] cp = p.toCharArray();
        
        // dp[i][j]:表示s的前i个字符，p的前j个字符是否能够匹配
        boolean[][] dp = new boolean[cs.length + 1][cp.length + 1];
        
        // 初期值
        // s为空，p为空，能匹配上
        dp[0][0] = true;
        // p为空，s不为空，必为false(boolean数组默认值为false，无需处理)
        
        // s为空，p不为空，由于*可以匹配0个字符，所以有可能为true
        for (int j = 1; j <= cp.length; j++) {
            if (cp[j - 1] == '*') {
                dp[0][j] = dp[0][j - 2];
            }
        }
        
        // 填格子
        for (int i = 1; i <= cs.length; i++) {
            for (int j = 1; j <= cp.length; j++) {
                // 文本串和模式串末位字符能匹配上
                if (cs[i - 1] == cp[j - 1] || cp[j - 1] == '.') {
                    dp[i][j] = dp[i - 1][j - 1];
                }
                else if (cp[j - 1] == '*') { // 模式串末位是*
                    // 模式串*的前一个字符能够跟文本串的末位匹配上
                    if (cs[i - 1] == cp[j - 2] || cp[j - 2] == '.') {
                        dp[i][j] = dp[i][j - 2]      // *匹配0次的情况
                                || dp[i - 1][j];     // *匹配1次或多次的情况
                    }
                    else { // 模式串*的前一个字符不能够跟文本串的末位匹配
                        dp[i][j] = dp[i][j - 2];     // *只能匹配0次
                    }
                }
            }
        }
        return dp[cs.length][cp.length];
    }
}
