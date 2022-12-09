package alg.leetcode;

import java.util.Stack;

public class P155MinStack1 {
    
    public static void main(String[] args) {
    
    }
    
    static class MinStack {
        int min = Integer.MAX_VALUE;
        Stack<Integer> stack = new Stack<>();
        public void push(int x) {
            //当前值更小
            if(x <= min){
                //将之前的最小值保存
                stack.push(min);
                //更新最小值
                min=x;
            }
            stack.push(x);
        }
    
        public void pop() {
            //如果弹出的值是最小值，那么将下一个元素更新为最小值
            if(stack.pop() == min) {
                min=stack.pop();
            }
        }
    
        public int top() {
            return stack.peek();
        }
    
        public int getMin() {
            return min;
        }
    }
}
