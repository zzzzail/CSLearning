package com.java.dsa.algorithm;

import java.util.Stack;

public class DFS {

    public void dfs(TreeNode root) {
        if (root == null) return;
        int n = 10;
        int[] visit = new int[n];
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.empty()) {
            TreeNode node = stack.pop();
            visit[node.val] = 1;
            // process node
            // 生成相关结点
            
            stack.push(node.left);
            stack.push(node.right);
        }
    }
    
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
    }
}
