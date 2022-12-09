package alg.leetcode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * https://leetcode-cn.com/problems/invert-binary-tree/
 * 翻转二叉树
 */
public class P226InvertBinaryTree1 {
    
    public static void main(String[] args) {
    
    }
    
    /**
     * 层次遍历二叉树
     * 每一个结点交换其顺序后再加入到队列中
     */
    public static TreeNode invertTree(TreeNode root) {
        if (root == null) return null;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node != null) {
                TreeNode left = node.left;
                node.left = node.right;
                node.right = left;
                queue.add(node.left);
                queue.add(node.right);
            }
        }
        return root;
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
