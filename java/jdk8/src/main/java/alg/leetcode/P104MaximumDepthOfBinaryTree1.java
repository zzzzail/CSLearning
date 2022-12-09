package alg.leetcode;

/**
 * https://leetcode-cn.com/problems/maximum-depth-of-binary-tree/
 * 二叉树的最大深度
 */
public class P104MaximumDepthOfBinaryTree1 {
    
    public static void main(String[] args) {
        TreeNode root1 = new TreeNode(3,
                new TreeNode(1, null, null),
                new TreeNode(2, null, null));
        int depth1 = maxDepth(root1);
        System.out.println(depth1);
    }
    
    public static int maxDepth(TreeNode root) {
        if (root == null) return 0;
        int left = maxDepth(root.left);
        int right = maxDepth(root.right);
        int max = Math.max(left, right);
        return max + 1;
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
