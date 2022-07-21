package com.dsa.leetcode.jzof;

/**
 * @author zail
 * @date 2022/7/21
 */
public class P55II {
    
    public static void main(String[] args) {
    
    }
    
    public boolean isBalanced(TreeNode root) {
        dfs(root, 1);
        return isBalanced;
    }
    
    boolean isBalanced = true;
    public int dfs(TreeNode node, int depth) {
        if (node == null) return depth - 1;
        int leftDepth = dfs(node.left, depth  + 1);
        int rightDepth = dfs(node.right, depth + 1);
        if (Math.abs(leftDepth - rightDepth) > 1) isBalanced = false;
        return Math.max(leftDepth, rightDepth); // 返回左右树的最大深度
    }
    
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        
        TreeNode(int x) {
            val = x;
        }
    }
}
