package com.java.dsa.leetcode;

public class P91DecodeWays1 {
    
    public static void main(String[] args) {
        int res1 = numDecodings("1123123");
        System.out.println(res1);
    }
    
    /**
     * 动态规划
     * 子问题： 要想知道第 i 个字符的解码方法数量，则要知道第 i - 1 个字符的解码方法数量
     * DP 数组定义： dp[i] 表示以 s[i] 结尾的前缀子串有多少种解码方法
     * DP 方程：
     * 当 s[i] 为 0 时 dp[i] = dp[i - 2]
     * 当 s[i] 为 1～9 时 dp[i] = dp[i - 1]
     * 11 <= int(dp[i - 1..i]) <= 26 时 dp[i] = dp[i - 1] + dp[i - 2]
     * 初始化： dp[0] = s[0] == '0' ? 0 : 1
     * 结果： dp[n - 1]
     */
    public static int numDecodings(String s) {
        char[] charArray = s.toCharArray();
        if (charArray[0] == '0') return 0;
        
        int n = s.length();
        int[] dp = new int[n];
        dp[0] = 1;
        for (int i = 1; i < n; i++) {
            if (charArray[i] != '0') dp[i] = dp[i - 1];
            
            int num = 10 * (charArray[i - 1] - '0') + (charArray[i] - '0');
            if (num >= 10 && num <= 26) {
                if (i == 1) dp[i]++;
                else dp[i] += dp[i - 2];
            }
        }
        return dp[n - 1];
    }
}
