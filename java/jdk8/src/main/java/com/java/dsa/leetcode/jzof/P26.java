package com.java.dsa.leetcode.jzof;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 树的子结构
 *
 * @author zail
 * @link https://leetcode.cn/problems/shu-de-zi-jie-gou-lcof/
 * @date 2022/7/17
 */
public class P26 {
    
    public static void main(String[] args) {
    
    }
    
    public boolean isSubStructure(TreeNode A, TreeNode B) {
        if (A == null || B == null) return false;
        return recur(A, B) || isSubStructure(A.left, B) || isSubStructure(A.right, B);
    }
    
    boolean recur(TreeNode A, TreeNode B) {
        // 匹配成功
        if (B == null) return true;
        // 匹配失败
        if (A == null || A.val != B.val) return false;
        // 继续递归乡下匹配
        return recur(A.left, B.left) && recur(A.right, B.right);
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
