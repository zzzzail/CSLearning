package com.dsa.leetcode;

/**
 * https://leetcode-cn.com/problems/minimum-depth-of-binary-tree/
 * 二叉树的最小深度
 */
public class P111MinimumDepthOfBinaryTree1 {
    
    public static void main(String[] args) {
    
    }
    
    public static int minDepth(TreeNode root) {
        if (root == null) return 0;
        // 1. 左孩子和有孩子都为空的情况，说明到达了叶子节点，直接返回1即可
        if (root.left == null && root.right == null) return 1;
        int left = minDepth(root.left);
        int right = minDepth(root.right);
        // 这里其中一个节点为空，说明 left 和 right 有一个必然为0，
        // 所以可以返回 left + right + 1
        if (root.left == null || root.right == null) return left + right + 1;
        return Math.min(left, right) + 1;
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
