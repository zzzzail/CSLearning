package com.dsa.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @link https://leetcode-cn.com/problems/largest-rectangle-in-histogram/
 * 柱状图中最大的矩形
 */
public class P84LargestRectangleInHistogram1 {
    
    public static void main(String[] args) {
    
    }
    
    /**
     * 单调栈法
     * 维护一个单调递增的栈，例如
     * [2, 1, 5, 6, 2, 3]
     * 栈中元素 1, 5, 6 是单调递增的，依次向前计算最大值即可。
     * 6 ～ 5 的最大值为 10
     * 6 ～ 1 的的最大值为 3
     * 最大值的计算为 ( cur - left - 1 ) * min(cur, left)
     */
    public static int largestRectangleArea(int[] heights) {
        int n = heights.length;
        int[] left = new int[n];
        int[] right = new int[n];
        
        // 单调栈
        Deque<Integer> monoStack = new ArrayDeque<>();
        for (int i = 0; i < n; ++i) {
            while (!monoStack.isEmpty() && heights[monoStack.peek()] >= heights[i]) {
                monoStack.pop();
            }
            left[i] = (monoStack.isEmpty() ? -1 : monoStack.peek());
            monoStack.push(i);
        }
        monoStack.clear(); // 清空栈
        for (int i = n - 1; i >= 0; --i) {
            while (!monoStack.isEmpty() && heights[monoStack.peek()] >= heights[i]) {
                monoStack.pop();
            }
            right[i] = (monoStack.isEmpty() ? n : monoStack.peek());
            monoStack.push(i);
        }
        
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            ans = Math.max(ans, (right[i] - left[i] - 1) * heights[i]);
        }
        return ans;
    }
    
}
