package alg.leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @link https://leetcode-cn.com/problems/binary-tree-level-order-traversal/
 * 二叉树的层序遍历
 */
public class P102BinaryTreeLevelOrderTraversal {
    
    public static void main(String[] args) {
        TreeNode root1 = new TreeNode(
                1,
                new TreeNode(2),
                new TreeNode(3)
        );
        List<List<Integer>> res1 = levelOrder2(root1);
        System.out.println(res1);
    }
    
    /**
     * 层序遍历，使用队列
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new LinkedList<>();
        if (root == null) return res;
        
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> level = new ArrayList<>(size);
            for (int i = 0; i < size; i++) {
                // 取出结点
                TreeNode node = queue.poll();
                if (node == null) continue;
                level.add(node.val);
                if (node.left != null) queue.add(node.left);
                if (node.right != null) queue.add(node.right);
            }
            res.add(level);
        }
        
        return res;
    }
    
    /**
     * 这个题还可以使用 DFS 实现
     */
    public static List<List<Integer>> levelOrder2(TreeNode root) {
        List<List<Integer>> res = new LinkedList<>();
        if (root == null) return res;
        dfs(root, 0, res);
        return res;
    }
    
    private static void dfs(TreeNode root, int depth, List<List<Integer>> res) {
        if (root == null) return;
        if (depth + 1 > res.size()) {
            res.add(new ArrayList<>());
        }
        res.get(depth).add(root.val);
        dfs(root.left, depth + 1, res);
        dfs(root.right, depth + 1, res);
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
