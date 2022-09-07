package com.java.dsa.leetcode.jzof;

/**
 * @author zail
 * @date 2022/7/19
 */
public class P21 {
    
    public static void main(String[] args) {
    
    }
    
    public int[] exchange(int[] nums) {
        if (nums == null) return null;
        if (nums.length < 2) return nums;
        
        int n = nums.length;
        int firstEven = -1; // 第一个偶数的位置
        for (int i = 0; i < n; i++) {
            if (nums[i] % 2 == 0) {  // 偶数
                firstEven = firstEven != -1 ? firstEven : i;
            }
            else {                   // 奇数
                if (firstEven == -1) continue;
                // swap(nums, i, firstEven);
                int t = nums[i]; nums[i] = nums[firstEven]; nums[firstEven] = t;
                firstEven++;
            }
        }
        return nums;
    }
    
    /**
     * 首尾双指针法
     */
    public int[] exchange2(int[] nums) {
        if (nums == null) return null;
        if (nums.length < 2) return nums;
        
        int n = nums.length;
        int left = 0, right = n - 1;
        while (left < right) {
            // 从左找开始找到第一个偶数
            while (left < right && (nums[left] & 1) == 1) ++left;
            // 从右边开始找到第一个奇数
            while (left < right && (nums[right] & 1) == 0) --right;
            int t = nums[left]; nums[left] = nums[right]; nums[right] = t;
            ++left;
            --right;
        }
        return nums;
    }
}
