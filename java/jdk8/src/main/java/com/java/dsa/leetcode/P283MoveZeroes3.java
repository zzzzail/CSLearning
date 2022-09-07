package com.java.dsa.leetcode;

import java.util.Arrays;

public class P283MoveZeroes3 {
    
    public static void main(String[] args) {
        int[] nums = {1, 2, 0, 0, 4, 9};
        moveZeroes(nums);
        System.out.println(Arrays.toString(nums));
    
        int[] nums2 = {0, 0, 1};
        moveZeroes(nums2);
        System.out.println(Arrays.toString(nums2));
    }
    
    /**
     * 很简单就是把 0 移动到最后呗
     */
    public static void moveZeroes(int[] nums) {
        int j = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                if (i != j) swap(nums, i, j);
                j++;
            }
        }
    }
    
    private static void swap(int[] nums, int i, int j) {
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }
}
