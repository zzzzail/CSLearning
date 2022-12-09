package alg.leetcode;

/**
 * @link https://leetcode-cn.com/problems/perfect-squares/
 * 完全平方数
 */
public class P279PerfectSquares {
    
    public static void main(String[] args) {
        System.out.println(numSquares(12));
        System.out.println(numSquares(13));
        System.out.println(numSquares(14));
    }
    
    /**
     * 注意： 12 由 3 个 4 组成，但是不能由 1 个 9 和 3 个 1 组成
     * <p>
     * 动态规划
     * 子问题： 若要计算数字 i 的最少使用的我完全平方数，要看 i - 1^2, 2^2, 3^2, 4^2,... 数字使用了
     * 多少个完全平方数，先加 1 再取其中最小。为什么先加 1，因为 1^2, 2^2, 3^2, 4^2 这些用掉了一个完全平方数。
     * 确定 DP 数组： dp[i] 记录要处理第几个数，这个数使用的完全平方数的个数
     * DP 方程： dp[i] = min(dp[i], dp[i - j * j] + 1)
     * dp[i] = min(dp[i - 0], dp[i - 1 * 1] + 1, dp[i - 2 * 2] + 1, ...)
     * 其中 dp[i - 0] 意为使用 i 个组成的完全平方数个数，即 i。
     * 初始值：dp[0] = 0, dp[1] = 1
     * 结果：dp[n]
     */
    public static int numSquares(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 0;
        for (int i = 1; i <= n; ++i) {
            dp[i] = i; // 初始时由 i 个 1 组成
            for (int j = 2; i - j * j >= 0; j++) {
                dp[i] = Math.min(dp[i], dp[i - j * j] + 1);
            }
        }
        return dp[n];
    }
    
}
