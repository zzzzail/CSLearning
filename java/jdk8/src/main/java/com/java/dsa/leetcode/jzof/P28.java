package com.java.dsa.leetcode.jzof;

/**
 * 对称的二叉树
 *
 * @author zail
 * @link https://leetcode.cn/problems/dui-cheng-de-er-cha-shu-lcof/
 * @date 2022/7/17
 */
public class P28 {
    
    public static void main(String[] args) {
    
    }
    
    public boolean isSymmetric(TreeNode root) {
        if (root == null) return true;
        return recur(root.left, root.right);
    }
    
    private boolean recur(TreeNode L, TreeNode R) {
        if (L == null && R == null) return true;
        if (L == null || R == null || L.val != R.val) return false;
        return recur(L.left, R.right) && recur(L.right, R.left);
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
