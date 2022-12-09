package alg.leetcodejzof;

/**
 * 数值的整数次方
 *
 * @author zail
 * @link https://leetcode.cn/problems/shu-zhi-de-zheng-shu-ci-fang-lcof/
 * @date 2022/7/21
 */
public class P16 {
    
    public static void main(String[] args) {
    
    }
    
    public double myPow(double x, int n) {
        if (x == 0) return 0;
        
        long b = n;
        double res = 1.0;
        
        // 处理负数次幂
        if (b < 0) {
            x = 1 / x;
            b = -b;
        }
        
        while (b > 0) {
            if ((b & 1) == 1) res *= x; // 若最低位为 1
            x *= x;
            b >>= 1; // 右移一位
        }
        return res;
    }
}
