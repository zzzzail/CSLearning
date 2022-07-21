package com.dsa.leetcode.jzof;

import java.util.Stack;

/**
 * 包含 min 函数的栈
 *
 * @author zail
 * @link https://leetcode.cn/problems/bao-han-minhan-shu-de-zhan-lcof/
 * @date 2022/6/29
 */
public class P30 {
    
    public static void main(String[] args) {
    
    }
    
    /**
     * 利用两个栈实现
     * 一个栈保存数据，另一个栈保存压栈时的最小值（从栈顶到栈底单调递增的栈，即单调栈）
     */
    static class MinStack {
        private Stack<Integer> stackData;
        private Stack<Integer> stackMin;
        public MinStack() {
            this.stackData = new Stack<>();
            this.stackMin = new Stack<>();
        }
        
        public void push(int x) {
            stackData.push(x);
            if (stackMin.isEmpty() || x <= stackMin.peek()) {
                stackMin.push(x);
            }
        }
        
        public void pop() {
            int val = stackData.pop();
            if (val == stackMin.peek()) {
                stackMin.pop();
            }
        }
        
        public int top() {
            return stackData.peek();
        }
        
        public int min() {
            return stackMin.peek();
        }
    }
}
