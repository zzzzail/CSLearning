package com.dsa.leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @link https://leetcode-cn.com/problems/find-largest-value-in-each-tree-row/
 * 在每个树的行中找最大值
 */
public class P515FindLargestValueInEachTreeRow1 {
    
    public static void main(String[] args) {
    
    }
    
    /**
     * 使用 BFS 遍历树并每次遍历的时候找出最大值即可
     */
    public List<Integer> largestValues(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;
        
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        
        while (!queue.isEmpty()) {
            int size = queue.size();
            int max = queue.peek().val;
            for (int i = 0; i < size; ++i) {
                TreeNode node = queue.poll();
                if (node == null) continue;
                if (node.val > max) max = node.val;
                if (node.left != null) queue.add(node.left);
                if (node.right != null) queue.add(node.right);
            }
            res.add(max);
        }
        
        return res;
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
