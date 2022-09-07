package com.java.dsa.leetcode;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @link https://leetcode-cn.com/problems/trapping-rain-water/
 * 接雨水
 */
public class P42TrappingRainWater1 {
    
    public static void main(String[] args) {
        int[] height1 = new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        int res1 = trap2(height1);
        System.out.println(res1);
    }
    
    public static int trap(int[] height) {
        Deque<Integer> deque1 = saveWater(height, 0, height.length - 1);
        Deque<Integer> deque2 = saveWater(height, height.length - 1, 0);
        int res = 0;
        for (int i = 0; i < height.length; i++) {
            res += Math.min(deque1.pollFirst(), deque2.pollLast());
        }
        return res;
    }
    
    private static Deque<Integer> saveWater(int[] height, int begin, int end) {
        Deque<Integer> res = new LinkedList<>();
        int h1 = height[begin];
        int endNext = (begin < end) ? end + 1 : end - 1;
        for (int i = begin; i != endNext; ) {
            int a = h1 - height[i];
            if (a > 0) {
                res.addLast(a);
            }
            else {
                res.addLast(0);
                h1 = height[i];
            }
            if (begin < end) ++i;
            else --i;
        }
        return res;
    }
    
    /**
     * 从左侧开始到右侧结束求积水量
     * 从右侧开始到左侧结束求积水量
     * 两个数组中元素最小值，相加得到结果
     */
    public static int trap2(int[] height) {
        int[] left = new int[height.length];
        int[] right = new int[height.length];
        int maxHeight = height[0];
        for (int i = 0; i < height.length; ++i) {
            int cur = maxHeight - height[i];
            if (cur > 0) {
                left[i] = cur;
            }
            else {
                left[i] = 0;
                maxHeight = height[i];
            }
        }
        maxHeight = height[height.length - 1];
        for (int i = height.length - 1; i >= 0; --i) {
            int cur = maxHeight - height[i];
            if (cur > 0) {
                right[i] = cur;
            }
            else {
                right[i] = 0;
                maxHeight = height[i];
            }
        }
        int res = 0;
        for (int i = 0; i < height.length; i++) {
            res += Math.min(left[i], right[i]);
        }
        return res;
    }
    
    /**
     * 使用单调递减的栈储存可能储水的量就可以，当找到一根比前面高的柱子，就可以计算
     * 接到的雨水。
     * 首先要理解一定是三根柱子才能形成低洼，形成低洼处才能个储备相应单位的水。
     * [0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1]
     *     ｜    ｜
     *     ———————
     *     1 和 2 处形成了低洼
     * 栈中的数据是：1, 0, 2
     * 其中 1 先入栈，0 入栈时判断 0 < 1，0 才会入栈。
     * 处理 2 时判断 2 < 0 不成立，所以需要处理 0, 2 处形成的低洼，会形成低洼的前提条件是是 0 前面还有一个值。
     * 因为是单调递减的栈，所以 0 前面的值一定比 0 要大。
     * 0 前面没有值说明这处低洼不成立。所以要判断 stack.isEmpty() 成立，则直接结束掉内循环。
     * 如何计算储水量？
     * current = 2
     * top = 0
     * 前后距离 distance = current 的下标 - 1 的下标 - 1 = 3 - 1 - 1 = 1
     * 上下的高度 height = min{2, 1} - 0 = 1
     * 所以储水量为 1 * 1 = 1
     */
    public static int trap3(int[] height) {
        int ans = 0, current = 0;
        Deque<Integer> stack = new LinkedList<>(); // 使用栈储存柱子的索引下标
        while (current < height.length) {
            // 内循环，当栈不为空且当前高度大于栈中存储下标的高度
            while (!stack.isEmpty() && height[current] > height[stack.peek()]) {
                int top = stack.pop();      // 取出栈顶元素
                if (stack.isEmpty()) break; // 若栈为空则跳出循环
                // 前后的距离
                int distance = current - stack.peek() - 1;
                // 高度
                int bounded_height = Math.min(height[current], height[stack.peek()]) - height[top];
                ans += distance * bounded_height;
            }
            stack.push(current++); // 将下标压入栈中
        }
        return ans;
    }
}
