package com.dsa.leetcode.jzof;

/**
 * 二叉搜索树的最近公共祖先
 *
 * @author zail
 * @link https://leetcode.cn/problems/er-cha-sou-suo-shu-de-zui-jin-gong-gong-zu-xian-lcof/
 * @date 2022/7/21
 */
public class P68I {
    
    public static void main(String[] args) {
    
    }
    
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root.val < p.val && root.val < q.val)
            return lowestCommonAncestor(root.right, p, q);
        if (root.val > p.val && root.val > q.val)
            return lowestCommonAncestor(root.left, p, q);
        return root;
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
