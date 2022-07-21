package com.dsa.leetcode;

/**
 * @link https://leetcode-cn.com/problems/sqrtx/
 * x 的平方根
 */
public class P69Sqrtx1 {
    
    public static void main(String[] args) {
        System.out.println(mySqrt(4));
        System.out.println(mySqrt(2147395599));
        System.out.println(mySqrt2(100));
    }
    
    /**
     * 使用二分法找平方根
     */
    public static int mySqrt(int x) {
        if (x == 0 || x == 1) return x;
        int low = 1, high = x;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (mid * mid > x)  high = mid - 1;
            else low = mid + 1;
        }
        return high;
    }
    
    /**
     * 使用牛顿迭代法
     */
    public static int mySqrt2(int x) {
        if (x == 0 || x == 1) return x;
        // 为什么用 long？
        long r = x;
        while (r * r > x) {
            r = (r + x / r) / 2;
        }
        return (int) r;
    }
    
}
