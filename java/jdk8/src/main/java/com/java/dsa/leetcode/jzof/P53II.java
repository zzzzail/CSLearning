package com.java.dsa.leetcode.jzof;

/**
 * 0 ~ n-1 中缺失的数字
 *
 * @author zail
 * @link https://leetcode.cn/problems/que-shi-de-shu-zi-lcof/
 * @date 2022/7/17
 */
public class P53II {
    
    public static void main(String[] args) {
    
    }
    
    public int missingNumber(int[] nums) {
        if (nums == null) return -1;
        int n = nums.length;
        int[] arr = new int[n];
        for (int num : nums) {
            if (0 <= num && num < n) {
                arr[num] = 1;
            }
        }
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 0) return i;
        }
        return n; // 如果数字都存在的话就返回 n
    }
}
