package com.dsa.leetcode;

/**
 * @link https://leetcode-cn.com/problems/lowest-common-ancestor-of-a-binary-tree/
 * 二叉树的最近公共祖先
 * 给定一个二叉树，找到该树中两个指定节点的最近公共祖先。
 */
public class P236LowestCommonAncestorOfABinaryTree1 {
    
    public static void main(String[] args) {
    
    }
    
    /**
     * 递归调用法
     */
    public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        /*
         递归结束条件：
         1. 当越过叶结点，则直接返回 null
         2. 当 root 等于 p，q 时，则直接返回 root
         */
        if (root == null) return null;
        if (root == p || root == q) return root;
        /*
        递推工作：
        1. 开启递归左子结点，返回值记为 left
        2. 开启递归右子结点，返回值极为 right
         */
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        /*
        返回处理：根据 left 和 right，可展开为四种情况：
        1. 当 left 和 right 同时为空：说明 root 的左右子树都不包含 p，q，返回 null
        2. 当 left 和 right 同时不为空：说明 p，q 分列在 root 的异侧（分别在左右子树），
           因此 root 为最近公共子祖先
        3. 当 left 为空，right 不为空，p，q 都不在 root 的左子树中，直接返回 right。具体
           可分为两种情况：
           （1）p，q 其中一个在 root 的右子树中，此时 right 指向 p（假设为 p）
           （2）p，q 两节点都在 root 的右子树中，此时的 right 指向最近公共祖先结点
        4. 当 left 不为空，right 为空：与情况 3 同理
        */
        if (left == null) return right;
        if (right == null) return left;
        return root; // p 和 q 都不为空的话，意 p，q 在两侧
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
