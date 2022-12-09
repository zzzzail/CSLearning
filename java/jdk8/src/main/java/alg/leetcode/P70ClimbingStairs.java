package alg.leetcode;

/**
 * https://leetcode-cn.com/problems/climbing-stairs/
 * 爬楼梯
 */
public class P70ClimbingStairs {
    
    public static void main(String[] args) {
        int n1 = climbStairs(45);
        System.out.println(n1);
        
        int n2 = climbStairs2(45);
        System.out.println(n2);
        System.out.println(n1 == n2);
        
    }
    
    /**
     * 这是一个递归求解问题
     * 到达 x 阶要么是从 x - 1 阶爬上来的，要么是从 x - 2 阶爬上来的。
     * 当 x = 0 时，返回 0
     * 当 x = 1 时，返回 1
     * 当 x = 2 时，返回 2
     *
     * 时间复杂度 O(2^n)
     * 时间复杂度太高了！
     */
    public static int climbStairs(int n) {
        if (n <= 2) return n; // 结束递归的条件
        return climbStairs(n - 1) + climbStairs(n - 2);
    }
    
    /**
     * 这个问题起始就是第 n 个斐波那契数列问题
     * 将递归变成循环
     */
    public static int climbStairs2(int n) {
        if (n <= 2) return n;
        int a = 1, b = 2, c = 3;
        for ( int i = 3; i <= n; i ++ ) {
            c = a + b;
            a = b;
            b = c;
        }
        return c;
    }
}
