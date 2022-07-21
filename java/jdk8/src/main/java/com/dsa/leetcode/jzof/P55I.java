package com.dsa.leetcode.jzof;

import javax.swing.plaf.IconUIResource;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 二叉树的深度
 *
 * @author zail
 * @link https://leetcode.cn/problems/er-cha-shu-de-shen-du-lcof/
 * @date 2022/7/21
 */
public class P55I {
    
    public static void main(String[] args) {
    
    }
    
    public int maxDepth(TreeNode root) {
        // return bfs(root);
        dfs(root, 1);
        return maxDepth;
    }
    
    public int bfs(TreeNode root) {
        if (root == null) return 0;
        int depth = 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            depth++;
            for (int i = queue.size(); i > 0; i--) {
                TreeNode cur = queue.poll();
                if (cur.left != null) queue.add(cur.left);
                if (cur.right != null) queue.add(cur.right);
            }
        }
        return depth;
    }
    
    int maxDepth = 0;
    public void dfs(TreeNode node, int depth) {
        if (node == null) return;
        maxDepth = Math.max(maxDepth, depth);
        dfs(node.left, depth + 1);
        dfs(node.right, depth + 1);
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
