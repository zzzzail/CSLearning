package alg.leetcodejzof;

/**
 * 二叉搜索树的后序遍历序列
 *
 * @author zail
 * @link https://leetcode.cn/problems/er-cha-sou-suo-shu-de-hou-xu-bian-li-xu-lie-lcof/
 * @date 2022/7/21
 */
public class P33 {
    
    public static void main(String[] args) {
    
    }
    
    public boolean verifyPostorder(int[] postorder) {
        return recur(postorder, 0, postorder.length - 1);
    }
    
    boolean recur(int[] postorder, int i, int j) {
        if (i >= j) return true;
        int p = i;
        while (postorder[p] < postorder[j]) p++;
        int m = p;
        while (postorder[p] > postorder[j]) p++;
        return p == j && recur(postorder, i, m - 1) && recur(postorder, m, j - 1);
    }
}
