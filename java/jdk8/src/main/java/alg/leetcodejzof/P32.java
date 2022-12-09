package alg.leetcodejzof;

import java.util.*;

/**
 * 从上到下打印二叉树
 *
 * @author zail
 * @link https://leetcode.cn/problems/cong-shang-dao-xia-da-yin-er-cha-shu-lcof/
 * @date 2022/7/17
 */
public class P32 {
    
    public static void main(String[] args) {
        P32 solution = new P32();
        
        TreeNode root1 = new TreeNode(3);
        root1.left = new TreeNode(9);
        root1.right = new TreeNode(20);
        int[] res1 = solution.levelOrder(root1);
        System.out.println(Arrays.toString(res1));
    }
    
    /**
     * 广度优先遍历 BFS
     */
    public int[] levelOrder(TreeNode root) {
        if (root == null) return new int[0];
        
        List<Integer> list = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            for (int i = queue.size(); i > 0; i--) {
                TreeNode cur = queue.poll();
                list.add(cur.val);
                if (cur.left != null) queue.add(cur.left);
                if (cur.right != null) queue.add(cur.right);
            }
        }
        int[] res = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            res[i] = list.get(i);
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
