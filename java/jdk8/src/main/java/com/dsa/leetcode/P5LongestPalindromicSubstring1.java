package com.dsa.leetcode;

/**
 * @link https://leetcode.cn/problems/longest-palindromic-substring/
 * 最长回文子串
 */
public class P5LongestPalindromicSubstring1 {
    
    public static void main(String[] args) {
    }
    
    /**
     * 暴力解法
     */
    public String longestPalindrome(String s) {
        if (s.length() <= 1) return s;
        
        char[] arr = s.toCharArray();
        int n = arr.length;
        int begin = 0;
        int maxLen = 1;
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                if (j - i + 1 > maxLen && isPalindromic(arr, i, j)) {
                    maxLen = j - i + 1;
                    begin = i;
                }
            }
        }
        return s.substring(begin, begin + maxLen);
    }
    
    public boolean isPalindromic(char[] arr, int left, int right) {
        while (left < right) {
            if (arr[left] != arr[right]) return false;
            ++left;
            --right;
        }
        return true;
    }
    
}
