package com.java.dsa.leetcode.jzof;

/**
 * 不用加减乘除做加法
 *
 * @author zail
 * @link https://leetcode.cn/problems/bu-yong-jia-jian-cheng-chu-zuo-jia-fa-lcof/
 * @date 2022/7/21
 */
public class P65 {
    
    public static void main(String[] args) {
    
    }
    
    public int add(int a, int b) {
        while (b != 0) {
            int c = (a & b) << 1; // 进位
            a = a ^ b;            // 无进位和
            b = c;
        }
        return a;
    }
}
