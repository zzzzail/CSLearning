package alg.leetcode;

import java.util.LinkedList;
import java.util.List;

/**
 * https://leetcode-cn.com/problems/n-ary-tree-postorder-traversal/
 * n 叉树的后续遍历
 */
public class P590NAryTreePostorderTraversal1 {
    
    public static void main(String[] args) {
    
    }
    
    public static List<Integer> postorder(Node root) {
        List<Integer> res = new LinkedList<>();
        postorderRecursion(root, res);
        return res;
    }
    
    private static void postorderRecursion(Node root, List<Integer> res) {
        if (root != null) {
            for (Node child : root.children) {
                postorderRecursion(child, res);
            }
            res.add(root.val);
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
