package com.java.dsa.leetcode;

/**
 * @link https://leetcode.cn/problems/counting-bits/
 * 比特位计数
 */
public class P338CountingBites1 {
    
    public static void main(String[] args) {
    
    }
    
    public int[] countBits(int n) {
        int[] res = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            int count = 0;
            for (int j = 0; j < 32; j++) {
                if (i >> j <= 0) break; // 加快了一点点
                if ((1 & (i >> j)) == 1) ++count;
            }
            res[i] = count;
        }
        return res;
    }
    
    /**
     * DP
     */
    public int[] countBits2(int n) {
        int[] bits = new int[n + 1];
        int highBit = 0;
        for (int i = 1; i <= n; i++) {
            if ((i & (i - 1)) == 0) {
                highBit = i;
            }
            bits[i] = bits[i - highBit] + 1;
        }
        return bits;
    }
}
