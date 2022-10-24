package com.java.dsa.leetcode;

/**
 * @author zhangxq
 * @since 2022/10/25
 */
public class P6ZigzagConversion02 {
    
    public static void main(String[] args) {
    
    }
    
    public String convert(String s, int r) {
        String res = "";
        if (r == 1) return s;
        
        // 2 * r - 2
        for (int i = 0; i < r; i++) {
            if (i == 0 || i == r - 1) {
                for (int j = i; j < s.length(); j += 2 * r - 2) {
                    res += s.charAt(j);
                }
            }
            else {
                for (int j = i, k = 2 * r - 2; j < s.length() || k < s.length(); j += 2 * r - 2, k += 2 * r - 2) {
                    if (j < s.length()) res += s.charAt(j);
                    if (k < s.length()) res += s.charAt(k);
                }
            }
        }
        
        return res;
    }
}
