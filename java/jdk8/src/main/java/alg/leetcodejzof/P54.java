package alg.leetcodejzof;

/**
 * @author zail
 * @date 2022/7/20
 */
public class P54 {
    
    public static void main(String[] args) {
    
    }
    
    int res, k;
    
    public int kthLargest(TreeNode root, int k) {
        this.k = k;
        dfs(root);
        return res;
    }
    
    void dfs(TreeNode root) {
        if (root == null) return;
        dfs(root.right);
        if (k == 0) return;
        if (--k == 0) res = root.val;
        dfs(root.left);
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
