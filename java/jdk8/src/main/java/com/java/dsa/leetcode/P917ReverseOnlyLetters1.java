package com.java.dsa.leetcode;

/**
 * @link https://leetcode.cn/problems/reverse-only-letters/
 * 仅仅反转字母
 */
public class P917ReverseOnlyLetters1 {
    
    public static void main(String[] args) {
    
    }
    
    /**
     * 1. 把非字母都单独保存下来，反转字母后，再将非字母原封不动加入进去
     * 2. 双指针，遇到非字母直接跳过
     */
    public String reverseOnlyLetters(String s) {
        byte[] arr = s.getBytes();
        int n = arr.length;
        int left = 0, right = n - 1;
        while (left < right) {
            while (left < right && !isLetter(arr[left])) ++left;
            while (left < right && !isLetter(arr[right])) --right;
            swap(arr, left, right);
            ++left;
            --right;
        }
        return new String(arr);
    }
    
    private boolean isLetter(byte c) {
        return ('A' <= c && c <= 'Z') || ('a' <= c && c <= 'z');
    }
    
    private void swap(byte[] arr, int a, int b) {
        byte t = arr[a];
        arr[a] = arr[b];
        arr[b] = t;
    }
    
    public String reverseOnlyLetters2(String s) {
        char[] c = s.toCharArray();
        int left = 0, right = s.length() - 1;
        while (left < right) {
            while (left < right && !Character.isLetter(c[left])) ++left;
            while (left < right && !Character.isLetter(c[right])) --right;
            char t = c[left];
            c[left++] = c[right];
            c[right--] = t;
        }
        return String.valueOf(c);
    }
    
}
