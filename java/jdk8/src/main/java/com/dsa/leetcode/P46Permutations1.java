package com.dsa.leetcode;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * @link https://leetcode-cn.com/problems/permutations/
 * 全排列
 */
public class P46Permutations1 {
    
    public static void main(String[] args) {
    
    }
    
    /**
     * 使用 DFS + 回溯
     * 时间复杂度：O(n * n!)
     * 空间复杂度：O(n * n!)
     */
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new LinkedList<>();
        if (nums == null || nums.length == 0) { return res;}
        boolean[] usage = new boolean[nums.length]; // 标记是使用哪些数字
        Deque<Integer> path = new LinkedList<>();
        dfs(nums, usage, 0, path, res);
        return res;
    }
    
    /**
     * 深度优先遍历
     * @param nums   对该数组进行全排列
     * @param usage  标记使用的了数组中的哪个值，没有使用哪个值
     * @param depth  当前进入的深度
     * @param path   深度优先遍历路径栈
     * @param res    结果集
     */
    private void dfs(int[] nums, boolean[] usage, int depth, Deque<Integer> path, List<List<Integer>> res) {
        // 结束条件是 count == nums.length
        if (depth == nums.length) {
            // path 变量只有一份，所以在深度遍历中要将 path 的值复制出来
            res.add(new LinkedList<>(path));
            return;
        }
        for (int i = 0; i < nums.length; ++i) {
            if (usage[i]) continue;  // 如果当前的数已经被使用了就跳过
            path.addLast(nums[i]);
            usage[i] = true;
            dfs(nums, usage, depth + 1, path, res);
            // 做相应的逆操作【回溯】
            usage[i] = false;
            path.removeLast();
        }
    }
    
}
