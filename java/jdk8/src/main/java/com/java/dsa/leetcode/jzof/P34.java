package com.java.dsa.leetcode.jzof;

import java.sql.Array;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 二叉树中和为某一值的路径
 *
 * @author zail
 * @link https://leetcode.cn/problems/er-cha-shu-zhong-he-wei-mou-yi-zhi-de-lu-jing-lcof/
 * @date 2022/7/19
 */
public class P34 {
    
    public static void main(String[] args) {
    
    }
    
    public List<List<Integer>> pathSum(TreeNode root, int target) {
        List<List<Integer>> res = new ArrayList<>();
        dfs(root, 0, target, new LinkedList<>(), res);
        return res;
    }
    
    private void dfs(TreeNode node, int sum, int target, LinkedList<Integer> path, List<List<Integer>> res) {
        if (node == null) return;
        
        path.addLast(node.val);
        sum += node.val; // 计算和
        // 如果条件满足则是一个结果
        if (sum == target && node.left == null && node.right == null) res.add(new ArrayList<>(path));
        dfs(node.left, sum, target, path, res);
        dfs(node.right, sum, target, path, res);
        path.removeLast(); // 回溯
    }
    
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        
        TreeNode() {
        }
        
        TreeNode(int val) {
            this.val = val;
        }
        
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
