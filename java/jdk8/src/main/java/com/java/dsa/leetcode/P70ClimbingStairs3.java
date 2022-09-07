
package com.java.dsa.leetcode;

public class P70ClimbingStairs3 {
    
    public static void main(String[] args) {
        int n1 = climbStairs(45);
        System.out.println(n1);
    
        int n2 = climbStairs(45);
        System.out.println(n2);
        System.out.println(n1 == n2);
    }
    
    /**
     * 爬楼梯问题就是斐波那契数列
     */
    public static int climbStairs(int n) {
        int a = 1, b = 2, c = 3;
        for (int i = 3; i <= n; i++) {
            c = a + b;
            a = b;
            b = c;
        }
        return c;
    }
}
