package alg.leetcode;

public class P70ClimbingStairs2 {
    
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
        if (n <= 2) return n; // 第一步一定是判断边界条件
        
        int a = 1, b = 2, c = 3;
        for (int i = 3; i <= n; i++) {
            c = a + b;
            a = b;
            b = c;
        }
        return c;
    }
}
