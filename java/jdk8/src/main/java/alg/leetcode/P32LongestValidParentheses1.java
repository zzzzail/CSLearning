package alg.leetcode;

/**
 * @link https://leetcode-cn.com/problems/longest-valid-parentheses/
 * 最长有效括号
 */
public class P32LongestValidParentheses1 {
    
    /**
     * 动态规划
     * 子问题： 求 dp[i] 是否是完整括号的问题是 dp[i- 1]、dp[i - 2]、dp[i - dp[i - 1] - 2] 的
     * 子问题。
     * DP 数组定义： dp[i] 表示以下标为 i 的字符结尾的最长有效子字符串的长度。
     * 有效字符串是以 ')' 结尾的，所以以 '(' 结尾的值一定是 0，只需要求解以 ')' 结尾在 dp 数组中对应
     * 位置的值。
     * DP 方程：
     * 第一种情况： s[i] = ')', s[i - 1] = '(' 也就是字符串形如 "...()"，我们可以推出：
     * dp[i] = dp[i − 2] + 2
     * 其中 2 是当前匹配的这一对括号，dp[i - 2] 就是之前已经匹配好的括号数
     * 第二种情况： s[i] = ')', s[i - 1] = ')' 也就是字符串形如 "...))"，我们可以推出：
     * 如果 dp[i - dp[i - 1] - 1] = '(' 那么
     * dp[i] = dp[i - dp[i - 1] - 2] + dp[i - 1] + 2
     * 初始化： 无 (注意从下标 1 开始即可)
     * 结果： 匹配过程中的最大值
     */
    public int longestValidParentheses(String s) {
        int n = s.length();
        int maxVal = 0;
        int[] dp = new int[n];
        for (int i = 1; i < n; i++) {
            if (s.charAt(i) == ')') {
                if (s.charAt(i - 1) == '(') {
                    dp[i] = (i >= 2 ? dp[i - 2] : 0) + 2;
                }
                else if (i - dp[i - 1] > 0 && s.charAt(i - dp[i - 1] - 1) == '(') {
                    dp[i] = (i - dp[i - 1] >= 2 ? dp[i - dp[i - 1] - 2] : 0) + dp[i - 1] + 2;
                }
                maxVal = Math.max(maxVal, dp[i]);
            }
        }
        return maxVal;
    }
    
}
