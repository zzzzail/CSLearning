package com.java.dsa.leetcode;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * @link https://leetcode-cn.com/problems/permutations-ii/
 * 全排列 2
 *
 * 给定一个可包含重复数字的序列 nums，按任意顺序返回所有不重复的全排列。
 * 注意：不重复的全排列
 */
public class P47PermutationsII1 {
    
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> res = new LinkedList<>();
        if (nums == null || nums.length == 0) return res;
        boolean[] usage = new boolean[nums.length]; // 记录元素使用
        Deque<Integer> path = new LinkedList<>();
        Arrays.sort(nums); // 先排序
        dfs(nums, usage, 0, path, res);
        return res;
    }
    
    private void dfs(int[] nums, boolean[] usage, int depth, Deque<Integer> path, List<List<Integer>> res) {
        if (depth == nums.length) {
            res.add(new LinkedList<>(path));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            /*
            剪枝条件：
            i > 0 为了保证 num[i - 1] 有意义
            usage[i - 1] 是因为 nums[i - 1] 在回退的过程中刚刚被撤销选择
             */
            if (usage[i] || (i > 0 && nums[i] == nums[i - 1] && !usage[i - 1])) continue;
            path.addLast(nums[i]);
            usage[i] = true;
            dfs(nums, usage, depth + 1, path, res);
            usage[i] = false;
            path.removeLast();
        }
    }
    
}
