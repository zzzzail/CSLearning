package com.dsa.leetcode;

import java.util.LinkedList;
import java.util.List;

/**
 * https://leetcode-cn.com/problems/validate-binary-search-tree/
 * 验证二叉搜索树
 */
public class P98ValidateBinarySearchTree1 {
    
    public static void main(String[] args) {
        TreeNode root1 = new TreeNode(2,
                new TreeNode(1, null, null),
                new TreeNode(3, null, null));
        isValidBST(root1);
    }
    
    /**
     * 1. 二叉搜索树有一个特性就是中序遍历的结果有序
     * 先中序遍历二叉树，并将中序遍历的结果加入到数组中。再判断数组是否有序即可。
     *
     */
    public static boolean isValidBST(TreeNode root) {
        List<Integer> list = new LinkedList<>();
        recursion(root, list);
        System.out.println(list);
        // 判断数组是否有序即可
        for (int i = 0; i < list.size(); ++i) {
            if (list.size() > i + 1 && list.get(i) >= list.get(i + 1)) return false;
        }
        return true;
    }
    
    private static void recursion(TreeNode root, List<Integer> list) {
        if (root != null) {
            recursion(root.left, list);
            list.add(root.val);
            recursion(root.right, list);
        }
    }
    
    // 二叉搜索树中序遍历的前一个结点值
    long pre = Long.MIN_VALUE;
    
    public boolean isValidBST2(TreeNode root) {
        if (root == null) return true;
        if (!isValidBST(root.left)) return false;
        // 如果当前节点小于等于中序遍历的前一个节点，说明不满足 BST ，返回 false；
        // 否则继续遍历
        if (pre >= root.val) return false;
        pre = root.val;
        // 访问右子树
        return isValidBST(root.right);
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
