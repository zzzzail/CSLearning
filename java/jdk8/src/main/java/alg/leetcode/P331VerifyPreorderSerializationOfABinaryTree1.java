package alg.leetcode;

import java.util.LinkedList;
import java.util.Objects;
import java.util.Stack;

/**
 * <a href="https://leetcode.cn/problems/verify-preorder-serialization-of-a-binary-tree/">
 * 验证二叉树前序序列化
 * </a>
 *
 * @author zail
 * @date 2021/5/25
 */
public class P331VerifyPreorderSerializationOfABinaryTree1 {
    
    public static void main(String[] args) {
    
    }
    
    /**
     * 首先字符串是前序遍历锁经过的结点值，其中包含了经过的空结点空结点
     * 验证这个字符串是否是正确的二叉树的前序序列化
     * <p>
     * 1. 用栈的方式实现
     */
    public boolean isValidSerialization(String preorder) {
        LinkedList<String> stack = new LinkedList<>();
        String[] strs = preorder.split(",");
        for (String str : strs) {
            stack.push(str);
            while (stack.size() >= 3
                    && "#".equals(stack.get(0))
                    && "#".equals(stack.get(1))
                    && !"#".equals(stack.get(2))) {
                stack.pop();
                stack.pop();
                stack.pop();
                stack.push("#");
            }
        }
        return stack.size() == 1 && stack.pop().equals("#");
    }
    
    /**
     * 计算出入度法
     *
     * 将空结点作为一个叶子节点，例如 # 算是一个叶结点
     * 所有的非空节点提供 2 个出度和 1 个入度（根除外）
     * 所有的空节点但提供 0 个出度和 1 个入度
     *
     * 我们在遍历的时候，计算 diff = outdegree - indegree. 当一个节点出现的时候，
     * diff - 1，因为它提供一个入度；当节点不是 #的时候，diff+2 (提供两个出度) 如果
     * 序列式合法的，那么遍历过程中 diff >=0 且最后结果为 0.
     */
    public boolean isValidSerialization2(String preorder) {
        String[] nodes = preorder.split(",");
        int diff = 1;
        for (String node : nodes) {
            --diff;
            if (diff < 0) return false;
            if (!node.equals("#"))
                diff += 2;
        }
        return diff == 0;
    }
    
}
