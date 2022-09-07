package com.java.dsa.leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @link https://leetcode-cn.com/problems/two-sum/
 * 给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出
 * 和为目标值 target 的那两个整数，并返回它们的数组下标。
 * <p>
 * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现。
 */
public class P1TwoSum1 {
    
    public static void main(String[] args) {
        P1TwoSum1 solution = new P1TwoSum1();
        int[] nums = new int[]{1, 2, 3, 4};
        int[] result1 = solution.twoSum(nums, 7);
        System.out.println(Arrays.toString(result1));
    }
    
    /**
     * 双重循环暴力求解
     * target = nums[x] + nums[y]
     * 时间负责度 O(n^2)
     * 空间复杂度 O(1)
     */
    public int[] twoSum(int[] nums, int target) {
        if (nums == null || nums.length < 2) return null;
        
        int[] result = new int[2];
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < nums.length; j++) {
                if (i != j && target == (nums[i] + nums[j])) {
                    result[0] = i;
                    result[1] = j;
                }
            }
        }
        return result;
    }
    
    /**
     * HashMap 法
     * target = nums[x] + nums[y]
     * 遍历 y 找 x，则 nums[y] = target - nums[x]
     * 暴力解法浪费的时间在于找 y，所以可以使用 HashMap 加快找 y 的速度。
     * 时间复杂度 O(n)
     * 空间复杂度 O(n)
     */
    public int[] twoSum2(int[] nums, int target) {
        if (nums == null || nums.length < 2) return null;
        
        Map<Integer, Integer> map = new HashMap<>();
        int t;
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(t = (target - nums[i]))) { // 若找到 nums[y]
                return new int[]{i, map.get(t)};
            }
            map.put(nums[i], i);
        }
        return new int[0];
    }
    
    /**
     * 这是很早以前写的代码。。。
     */
    public int[] twoSum3(int[] nums, int target) {
        // 利用map键唯一性解
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int x = target - nums[i];
            if (map.containsKey(x) && map.get(x) != i) {
                return new int[]{map.get(x), i};
            }
            map.put(nums[i], i);
        }
        return new int[0];
    }
}
