package com.dsa.leetcode;

/**
 * @link https://leetcode.cn/problems/string-to-integer-atoi/
 * 字符串转换成整数（atoi）
 */
public class P8StringToIntegerAtoi1 {
    
    public static void main(String[] args) {
        P8StringToIntegerAtoi1 solution = new P8StringToIntegerAtoi1();
        System.out.println(solution.myAtoi("42"));
        System.out.println(solution.myAtoi("+42"));
        System.out.println(solution.myAtoi("-42"));
        System.out.println(solution.myAtoi("01"));
        System.out.println(solution.myAtoi("010"));
        System.out.println(solution.myAtoi("   010   "));
        System.out.println(solution.myAtoi("-9223372036854775808"));
        System.out.println(solution.myAtoi("18446744073709551617"));
        System.out.println(solution.myAtoi("2147483648"));
    }
    
    /**
     * 暴力解法
     * 注意使用 long 类型网上加，且要判断是否超过 int 所表示的范围，若超过，则返回最大值或最小值即可。
     */
    public int myAtoi(String s) {
        // 1. 丢弃无用的空格
        s = s.trim();
        if (s.length() == 0) return 0;
        boolean positive = true; // 默认是正整数
        int i = 0;
        if (s.charAt(0) == '-') {
            i = 1;
            positive = false;
        }
        if (s.charAt(0) == '+') {
            i = 1;
            positive = true;
        }
        long l = 0;
        for (; i < s.length(); ++i) {
            if ('0' <= s.getBytes()[i] && s.getBytes()[i] <= '9') {
                l *= 10;
                l += s.getBytes()[i] - '0';
                if (l > Integer.MAX_VALUE) {
                    return positive ? Integer.MAX_VALUE : Integer.MIN_VALUE;
                }
            }
            else break;
        }
        l = positive ? l : -l;
        return (int) l;
    }
    
}
