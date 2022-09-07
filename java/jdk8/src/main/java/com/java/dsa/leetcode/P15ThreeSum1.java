package com.java.dsa.leetcode;

import java.util.*;

/**
 * https://leetcode-cn.com/problems/3sum/
 * 三数之和
 * 给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得
 * a + b + c = 0 ？请你找出所有和为 0 且不重复的三元组。
 * <p>
 * 输入：nums = [-1,0,1,2,-1,-4]
 * 输出：[[-1,-1,2],[-1,0,1]]
 */
public class P15ThreeSum1 {
    
    public static void main(String[] args) {
        int[] nums = new int[]{-1, 0, 1, 2, -1, -4};
        List<List<Integer>> result1 = threeSum2(nums);
        System.out.println(result1);
        
        int[] nums2 = new int[]{0, 0, 0};
        List<List<Integer>> result2 = threeSum2(nums2);
        System.out.println(result2);
    }
    
    /**
     * nums[x] + nums[y] = - nums[z]
     * 利用 HashMap 记录下 nums[z] 的位置
     * 两次遍历，若找到 x 和 y，x != y ！= z，nums[x] + nums[y] = - nums[z]，且
     * - nums[z] 在 map 中存在，则 x，y，z 三个值就是要找的结果。
     */
    public static List<List<Integer>> threeSum(int[] nums) {
        if (nums == null || nums.length < 3) return new ArrayList<>();
        
        List<List<Integer>> result = new ArrayList<>();
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i + 1; j < nums.length - 2; j++) {
                if (map.containsKey(nums[j])) {
                    List<Integer> l = map.get(nums[j]);
                    result.add(Arrays.asList(nums[j], l.get(0), l.get(1)));
                }
                else {
                    int mark = -nums[i] - nums[j];
                    map.put(mark, Arrays.asList(nums[i], nums[j]));
                }
            }
        }
        return result;
    }
    
    /**
     * 先排序
     * 顺序找一个 c 位，a 位置在其前面，b 位置在其后面
     * nums[a] + nums[b] + nums[c] = 0，则是题解
     * nums[a] + nums[b] + nums[c] > 0，说明太强了，需要减弱一下战斗力，故 b 位置向左移动
     * nums[a] + nums[b] + nums[c] < 0，说明太弱了，须要加强一下战斗力，故 a 位置向右移动
     */
    public static List<List<Integer>> threeSum2(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        int len = nums.length;
        Arrays.sort(nums); // 先排序，最左边是最弱的，最右边是最强的
        for (int i = 0; i < len; i++) {
            if (nums[i] > 0) return res; // 如果走到 > 0 处说明后面没有解了
            if (i > 0 && nums[i] == nums[i - 1]) continue; // 数重复直接过
            int l = i + 1, r = len - 1;
            while (l < r) {
                int t = nums[i] + nums[l] + nums[r];
                if (t == 0) {
                    res.add(Arrays.asList(nums[i], nums[l], nums[r]));
                    while (l < r && nums[l] == nums[++l]) {}; // 过掉重复的元素
                    while (l < r && nums[r] == nums[--r]) {}; // 过掉重复的元素
                }
                else if (t < 0) l++; // 太弱了
                else r--;            // 太强了
            }
        }
        return res;
    }
    
}