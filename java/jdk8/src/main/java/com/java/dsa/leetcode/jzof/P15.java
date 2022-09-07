package com.java.dsa.leetcode.jzof;

/**
 * 二进制中 1 的个数
 *
 * @author zail
 * @link https://leetcode.cn/problems/er-jin-zhi-zhong-1de-ge-shu-lcof/
 * @date 2022/7/21
 */
public class P15 {
    
    public static void main(String[] args) {
    
    }
    
    public int hammingWeight(int n) {
        int res = 0;
        for (int i = 0; i < 32; i++) {
            if ((n & 1) == 1) res++;
            n >>= 1;
        }
        return res;
    }
}
