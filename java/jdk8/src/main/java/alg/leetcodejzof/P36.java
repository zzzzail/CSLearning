package alg.leetcodejzof;

/**
 * 二叉搜索树与双向链表
 *
 * @author zail
 * @link https://leetcode.cn/problems/er-cha-sou-suo-shu-yu-shuang-xiang-lian-biao-lcof/
 * @date 2022/7/19
 */
public class P36 {
    
    public static void main(String[] args) {
    
    }
    
    Node pre, head;
    
    public Node treeToDoublyList(Node root) {
        if (root == null) return null;
        dfs(root);
        head.left = pre;
        pre.right = head;
        return head;
    }
    
    void dfs(Node cur) {
        if (cur == null) return;
        dfs(cur.left);
        if (pre != null) pre.right = cur;
        else head = cur;
        cur.left = pre;
        pre = cur;
        dfs(cur.right);
    }
    
    static class Node {
        public int val;
        public Node left;
        public Node right;
        
        public Node() {
        }
        
        public Node(int _val) {
            val = _val;
        }
        
        public Node(int _val, Node _left, Node _right) {
            val = _val;
            left = _left;
            right = _right;
        }
    }
}
