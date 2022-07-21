package com.dsa.leetcode;

public class P11ContainerWithMostWater2 {
    
    public static void main(String[] args) {
        int[] height = new int[]{1,8,6,2,5,4,8,3,7};
        int max = maxArea2(height);
        System.out.println(max); // 49
    }
    
    /**
     * 最大容量
     * 前后双指针
     */
    public static int maxArea2(int[] height) {
        int max = 0;
        for (int i = 0, j = height.length - 1; i < j; ) {
            int minHeight = height[i] <= height[j] ? height[i++] : height[j--];
            int area = minHeight * ( j - i + 1); // 这里的 + 1 是补上一步的提前计算
            max = Math.max(max, area);
        }
        return max;
    }
}
