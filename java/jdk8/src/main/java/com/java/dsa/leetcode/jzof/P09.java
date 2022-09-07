package com.java.dsa.leetcode.jzof;

import java.util.Stack;

/**
 * 使用两个栈实现一个队列
 *
 * @author zail
 * @link https://leetcode.cn/problems/yong-liang-ge-zhan-shi-xian-dui-lie-lcof/
 * @date 2022/6/29
 */
public class P09 {
    
    public static void main(String[] args) {
        CQueue cQueue = new CQueue();
        cQueue.appendTail(5);
        cQueue.appendTail(2);
        int val1 = cQueue.deleteHead();
        System.out.println(val1);
        int val2 = cQueue.deleteHead();
        System.out.println(val2);
    }
    
    static class CQueue {
        
        private Stack<Integer> positiveOrderStack;
        
        private Stack<Integer> reverseOrderStack;
        
        public CQueue() {
            positiveOrderStack = new Stack<>();
            reverseOrderStack = new Stack<>();
        }
        
        // 在队尾元素
        public void appendTail(int value) {
            positiveOrderStack.push(value);
        }
        
        // 删除队头元素
        public int deleteHead() {
            if (reverseOrderStack.size() == 0) {
                int n = positiveOrderStack.size();
                if (n == 0) return -1;
                for (int i = 0; i < n; i++) {
                    Integer val = positiveOrderStack.pop();
                    reverseOrderStack.push(val);
                }
            }
            return reverseOrderStack.pop();
        }
    }
}
