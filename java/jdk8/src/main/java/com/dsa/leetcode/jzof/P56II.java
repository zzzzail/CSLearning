package com.dsa.leetcode.jzof;

/**
 * 数组中数字出现的次数 II
 *
 * @author zail
 * @link https://leetcode.cn/problems/shu-zu-zhong-shu-zi-chu-xian-de-ci-shu-ii-lcof/
 * @date 2022/7/21
 */
public class P56II {
    
    public static void main(String[] args) {
    
    }
    
    public int singleNumber(int[] nums) {
        int ones = 0, twos = 0;
        for (int num : nums) {
            ones = ones ^ num & ~twos;
            twos = twos ^ num & ~ones;
        }
        return ones;
    }
}
