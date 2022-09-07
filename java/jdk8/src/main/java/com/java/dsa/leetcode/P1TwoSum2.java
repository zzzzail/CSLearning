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
public class P1TwoSum2 {
    
    public static void main(String[] args) {
        P1TwoSum2 solution = new P1TwoSum2();
        int[] nums = new int[]{1, 2, 3, 4};
        int[] result1 = solution.twoSum(nums, 7);
        System.out.println(Arrays.toString(result1));
    }
    
    /**
     * 两数之和 nums[x] + nums[y] = target
     */
    public int[] twoSum(int[] nums, int target) {
        int t;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            t = target - nums[i];
            if (map.containsKey(t) && map.get(t) != i) {
                return new int[]{i, map.get(t)};
            }
            map.put(nums[i], i);
        }
        return new int[0];
    }
}
