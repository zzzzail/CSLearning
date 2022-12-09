package alg.leetcode;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class P429NAryTreeLevelOrderTraversal {
    
    public static void main(String[] args) {
    
    }
    
    /**
     * 层序遍历需要队列
     */
    public List<List<Integer>> levelOrder(Node root) {
        List<List<Integer>> res = new LinkedList<>();
        if (root == null) return res;
        
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            List<Integer> stageRes = new LinkedList<>();
            int size = queue.size();
            for (int i = 0; i < size; ++i) {
                 Node node = queue.poll();
                if (node != null) {
                    stageRes.add(node.val);
                    queue.addAll(node.children);
                }
            }
            res.add(stageRes);
        }
        return res;
    }
    
    static class Node {
        public int val;
        public List<Node> children;
        
        public Node() {
        }
        
        public Node(int _val) {
            val = _val;
        }
        
        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    }
}
