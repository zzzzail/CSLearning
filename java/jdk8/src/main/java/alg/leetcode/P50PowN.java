package alg.leetcode;

/**
 * @link https://leetcode-cn.com/problems/powx-n/
 * Pow(x, n)
 * x 的 n 次方
 */
public class P50PowN {
    
    public static void main(String[] args) {
        double res1 = myPow(2, 2);
        System.out.println(res1);
    }
    
    /**
     * 暴力解法
     */
    public static double myPow(double x, int n) {
        if (x == 1 || n == 0) return 1;
        if (n < 0) {
            x = 1 / x; // x 取成倒数
            n = -n;    // n 取成正的
        }
        double res = 1;
        for (int i = 0; i < n; i++) {
            res *= x;
        }
        return res;
    }
    
    /**
     * 递归快速迭代法
     */
    public static double myPow2(double x, int n) {
        long N = n;
        return N >= 0 ? quickMul(x, N) : 1.0 / quickMul(x, -N);
    }
    
    public static double quickMul(double x, long N) {
        if (N == 0) {
            return 1.0;
        }
        double res = quickMul(x, N / 2);
        return N % 2 == 0 ? res * res : res * res * x;
    }
    
    /*
     牛顿迭代法？
     x = 2, n = 7
     7 = (0111)_2
     x ^ 7 = (0 * x ^ 8) * (1 * x ^ 4) * (1 * x ^ 2) * (1 * x ^ 1)
     每一个二进制位，若为 1 则乘
     */
    public static double quickMul2(double x, long N) {
        double ans = 1.0;
        // 贡献的初始值为 x
        double x_contribute = x;
        // 在对 N 进行二进制拆分的同时计算答案
        while (N > 0) {
            if (N % 2 == 1) {
                // 如果 N 二进制表示的最低位为 1，那么需要计入贡献
                ans *= x_contribute;
            }
            // 将贡献不断地平方
            x_contribute *= x_contribute;
            // 舍弃 N 二进制表示的最低位，这样我们每次只要判断最低位即可
            N /= 2;
        }
        return ans;
    }
    
}
