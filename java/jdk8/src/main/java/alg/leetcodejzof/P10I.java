package alg.leetcodejzof;

/**
 * 斐波那契数列
 *
 * @author zail
 * @link https://leetcode.cn/problems/fei-bo-na-qi-shu-lie-lcof/
 * @date 2022/7/18
 */
public class P10I {
    
    public static void main(String[] args) {
        P10I solution = new P10I();
        
        int res1 = solution.fib(19);
        System.out.println(res1);
    
        System.out.println(solution.fib(43));
        System.out.println(solution.fib(44));
        System.out.println(solution.fib(45));
    }
    
    /**
     * 子问题： 要想求出第 n 个斐波那契数，就要求出第 n - 1 个斐波那契数和第 n - 2 个斐波那契数，
     * 并将两者相加，即可得到第 n 个斐波那契数。
     * dp 数组定义： dp[i] 为第 i 个斐波那契数的值
     * 初始化数组： dp[0] = 0; dp[1] = 1;
     * dp 地推公式： dp[i] = dp[i - 1] + dp[i - 2]
     * 结果： dp[n]
     */
    public int fib(int n) {
        // 边界条件 F(0) = 0, F(1) = 1
        if (n < 2) return n;
        
        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            dp[i] = ( dp[i - 1] + dp[i - 2] ) % 1000000007;
        }
        return dp[n];
    }
    
    public int fib2(int n) {
        if (n < 2) return n;
        return fib2(n - 1) + fib2(n - 2);
    }
}
