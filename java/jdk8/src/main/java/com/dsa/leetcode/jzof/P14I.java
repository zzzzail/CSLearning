package com.dsa.leetcode.jzof;

/**
 * 剪绳子
 *
 * @author zail
 * @link https://leetcode.cn/problems/jian-sheng-zi-lcof/
 * @date 2022/7/22
 */
public class P14I {
    
    public static void main(String[] args) {
    
    }
    
    public int cuttingRope(int n) {
        if (n <= 3) return n - 1;
        
        int a = n / 3, b = n % 3;
        if (b == 0) {
            return (int) Math.pow(3, a);
        }
        else if (b == 1) {
            // 一个 3 * 1 转换成 2 * 2，所以要 * 4
            return (int) Math.pow(3, a - 1) * 4;
        }
        else { // if (b == 2) {
            return (int) Math.pow(3, a) * 2;
        }
    }
}
