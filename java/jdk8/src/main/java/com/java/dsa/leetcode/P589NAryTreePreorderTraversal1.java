package com.java.dsa.leetcode;

import java.util.LinkedList;
import java.util.List;

public class P589NAryTreePreorderTraversal1 {
    
    public static void main(String[] args) {
    
    }
    
    public List<Integer> preorder(Node root) {
        List<Integer> res = new LinkedList<>();
        preorderRecursion(root, res);
        return res;
    }
    
    private void preorderRecursion(Node root, List<Integer> res) {
        if (root != null) {
            res.add(root.val);
            for (Node child : root.children) {
                preorderRecursion(child, res);
            }
        }
    }
    
    static class Node {
        public int val;
        public List<Node> children;
        
        public Node() {}
        
        public Node(int _val) {
            val = _val;
        }
        
        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    }
}
