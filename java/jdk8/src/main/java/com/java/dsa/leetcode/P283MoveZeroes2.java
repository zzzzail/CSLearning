package com.java.dsa.leetcode;

import java.util.Arrays;

public class P283MoveZeroes2 {
    
    public static void main(String[] args) {
        int[] nums = {1, 2, 0, 0, 4, 9};
        moveZeroes(nums);
        System.out.println(Arrays.toString(nums));
    
        int[] nums2 = {0, 0, 1};
        moveZeroes(nums2);
        System.out.println(Arrays.toString(nums2));
    }
    
    /**
     * 将数组中的 0 移动到最后
     * 滚雪球算法
     * i 指向要处理的元素位置，
     * j 指向数组中的第一个 0，[j, i) 中全部都是 0。
     */
    public static void moveZeroes(int[] nums) {
        if (nums == null || nums.length == 0) return;
    
        for (int i = 0, j = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                if (j != i) swap(nums, i, j);
                j++;
            }
        }
    }
    
    private static void swap(int[] nums, int i1, int i2) {
        int t = nums[i1];
        nums[i1] = nums[i2];
        nums[i2] = t;
    }
}
