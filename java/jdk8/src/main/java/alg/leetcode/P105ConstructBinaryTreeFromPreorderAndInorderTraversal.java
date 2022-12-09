package alg.leetcode;

/**
 * https://leetcode-cn.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/
 * 从前序与中序遍历序列构造二叉树
 * <p>
 * 前序确定根结点，后续遍历分左右
 */
public class P105ConstructBinaryTreeFromPreorderAndInorderTraversal {
    
    public static void main(String[] args) {
        int[] preorder1 = new int[]{3, 9, 20, 15, 7};
        int[] inorder1 = new int[]{9, 3, 15, 20, 7};
        TreeNode res1 = buildTree(preorder1, inorder1);
        System.out.println(res1);
    }
    
    public static TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder == null || preorder.length == 0) return null;
        if (inorder == null || inorder.length == 0) return null;
        if (preorder.length != inorder.length) return null;
        return recursion(preorder, 0, preorder.length - 1, inorder);
    }
    
    /**
     * [preBegin, preEnd]
     */
    private static TreeNode recursion(int[] preorder, int preBegin, int preEnd, int[] inorder) {
        // 递归结束条件
        if (preEnd - preBegin < 0) return null;
        // 确定 rootVal 是根
        TreeNode root = new TreeNode(preorder[preBegin]);
        // search rootVal in inorder[]
        int count = 0;
        for (int i = 0; i < inorder.length; ++i) {
            if (inorder[i] == root.val) {
                inorder[i] = Integer.MAX_VALUE;
                break;
            }
            if (inorder[i] != Integer.MAX_VALUE) ++count;
        }
        root.left = recursion(preorder, preBegin + 1, preBegin + count, inorder);
        root.right = recursion(preorder, preBegin + count + 1, preEnd, inorder);
        return root;
    }
    
    /**
     * 2. 利用 找中序遍历中的结点方法
     * https://leetcode-cn.com/problems/zhong-jian-er-cha-shu-lcof/solution/shou-hua-tu-jie-you-yu-guan-fang-bu-yong-ujid/
     */
    static int inp = 0, prep = 0;
    public static TreeNode buildTree2(int[] preorder, int[] inorder) {
        return build2(preorder, inorder, Integer.MAX_VALUE);
    }
    
    public static TreeNode build2(int[] preorder, int[] inorder, int stop) {
        if (prep >= preorder.length) return null;
        //如果stop与中序inp相等，说明左树完结，返回
        if (inorder[inp] == stop) {
            inp++;
            return null;
        }
        //创造节点加入树，同时调用，而节点的值加入了stop栈（调用体现）
        TreeNode node = new TreeNode(preorder[prep++]);
        node.left = build2(preorder, inorder, node.val);
        node.right = build2(preorder, inorder, stop);
        return node;
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
