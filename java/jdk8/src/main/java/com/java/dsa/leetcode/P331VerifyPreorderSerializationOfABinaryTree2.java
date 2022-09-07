package com.java.dsa.leetcode;

import java.util.LinkedList;
import java.util.Stack;

/**
 * <a href="https://leetcode.cn/problems/verify-preorder-serialization-of-a-binary-tree/">
 * 验证二叉树前序序列化
 * </a>
 *
 * @author zail
 * @date 2021/5/25
 */
public class P331VerifyPreorderSerializationOfABinaryTree2 {
    
    public static void main(String[] args) {
    
    }
    
    public boolean isValidSerialization(String preorder) {
        LinkedList<String> stack = new LinkedList<>();
        String[] strs = preorder.split(",");
        for (String str : strs) {
            stack.push(str);
            while ( stack.size() >= 3 &&
                    "#".equals(stack.get(0)) &&
                    "#".equals(stack.get(1)) &&
                    !"#".equals(stack.get(2)) ) {
                stack.pop();
                stack.pop();
                stack.pop();
                stack.push("#");
            }
        }
        return stack.size() == 1 && "#".equals(stack.pop());
    }
    
}
