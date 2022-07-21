package com.dsa.leetcode;

/**
 * @link https://leetcode.cn/problems/power-of-two/
 * 2 的幂
 */
public class P231PowerOfTwo1 {
    
    public static void main(String[] args) {
    
    }
    
    /**
     * 只有一个 1 就是 2 的幂
     */
    public boolean isPowerOfTwo(int n) {
        int count = 0;
        for (int i = 0; i < 32; i++) {
            if (n >> i > 0 && (1 & (n >> i)) == 1) ++count;
        }
        return count == 1;
    
    }
    
    /**
     * 按位判断
     */
    public boolean isPowerOfTwo2(int n) {
        return n > 0 && (n & (n - 1)) == 0;
    }
}
