package alg.leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * @link https://leetcode-cn.com/problems/binary-tree-inorder-traversal/
 * 94. 二叉树的中序遍历
 */
public class P94BinaryTreeInorderTraversal1 {
    
    public static void main(String[] args) {
    }
    
    /**
     * 使用中栈
     */
    public List<Integer> inorderTraversalUsingStack(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        while (cur != null || !stack.isEmpty()) {
            if (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }
            else {
                cur = stack.pop();
                list.add(cur.val);
                cur = cur.right;
            }
        }
        return list;
    }
    
    /**
     * 使用递归的方式遍历
     */
    public List<Integer> inorderTraversal2(TreeNode root) {
        List<Integer> res = new LinkedList<>();
        inorderTraversalRecursion(root, res);
        return res;
    }
    
    public static void inorderTraversalRecursion(TreeNode root, List<Integer> res) {
        if (root != null) {
            inorderTraversalRecursion(root.left, res);
            res.add(root.val);
            inorderTraversalRecursion(root.right, res);
        }
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

