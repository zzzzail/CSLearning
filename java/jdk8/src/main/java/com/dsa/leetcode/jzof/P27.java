package com.dsa.leetcode.jzof;

/**
 * 二叉树的镜像
 *
 * @author zail
 * @link https://leetcode.cn/problems/er-cha-shu-de-jing-xiang-lcof/
 * @date 2022/7/17
 */
public class P27 {
    
    public static void main(String[] args) {
    
    }
    
    public TreeNode mirrorTree(TreeNode root) {
        recur(root);
        return root;
    }
    
    public void recur(TreeNode root) {
        if (root == null) return;
        // 交换 left 和 right 的值
        TreeNode l = root.left;
        root.left = root.right;
        root.right = l;
        mirrorTree(root.left);
        mirrorTree(root.right);
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
