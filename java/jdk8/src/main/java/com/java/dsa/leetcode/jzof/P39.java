package com.java.dsa.leetcode.jzof;

/**
 * 数组中出现次数超过一半的数字
 *
 * @author zail
 * @link https://leetcode.cn/problems/shu-zu-zhong-chu-xian-ci-shu-chao-guo-yi-ban-de-shu-zi-lcof/
 * @date 2022/7/21
 */
public class P39 {
    
    public static void main(String[] args) {
    
    }
    
    public int majorityElement(int[] nums) {
        int x = 0, votes = 0;
        for (int num : nums) {
            if (votes == 0) x = num;
            votes += num == x ? 1 : -1;
        }
        return x;
    }
}
