package alg.leetcodejzof;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 从上到下打印二叉树 III
 *
 * @author zail
 * @link https://leetcode.cn/problems/cong-shang-dao-xia-da-yin-er-cha-shu-iii-lcof/
 * @date 2022/7/17
 */
public class P32III {
    
    public static void main(String[] args) {
    
    }
    
    /**
     * 之字型打印
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;
        
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            LinkedList<Integer> list = new LinkedList<>();
            for (int i = queue.size(); i > 0; i--) {
                TreeNode cur = queue.poll();
                // 偶数层正序加入 list
                if (res.size() % 2 == 0) list.addLast(cur.val);
                // 奇数层倒叙加入 list
                else list.addFirst(cur.val);
                if (cur.left != null) queue.add(cur.left);
                if (cur.right != null) queue.add(cur.right);
            }
            res.add(list);
        }
        return res;
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
