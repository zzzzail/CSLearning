package com.dsa.leetcode.jzof;

/**
 * @author zail
 * @date 2022/7/19
 */
public class P57 {
    
    public static void main(String[] args) {
    
    }
    
    public int[] twoSum(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left < right) {
            int num = nums[left] + nums[right];
            if (num == target) return new int[]{nums[left], nums[right]};
            else if (num < target) left++;
            else right--; // if (num > target)
        }
        return null;
    }
}
