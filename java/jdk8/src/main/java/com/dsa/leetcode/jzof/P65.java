package com.dsa.leetcode.jzof;

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
        while (b != 0) {           // 当进位为 0 时跳出
            int c = (a & b) << 1;  // c = 进位
            a ^= b;                // a = 非进位和
            b = c;                 // b = 进位
        }
        return a;
    }
}
