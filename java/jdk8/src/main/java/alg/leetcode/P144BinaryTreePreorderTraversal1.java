package alg.leetcode;

import java.util.LinkedList;
import java.util.List;

public class P144BinaryTreePreorderTraversal1 {
    
    public static void main(String[] args) {
    
    }
    
    /**
     * 使用递归的方式遍历
     */
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> res = new LinkedList<>();
        preorderTraversalRecursion(root, res);
        return res;
    }
    
    private void preorderTraversalRecursion(TreeNode root, List<Integer> res) {
        if (root != null) {
            res.add(root.val);
            preorderTraversalRecursion(root.left, res);
            preorderTraversalRecursion(root.right, res);
        }
    }
    
    public class TreeNode {
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
