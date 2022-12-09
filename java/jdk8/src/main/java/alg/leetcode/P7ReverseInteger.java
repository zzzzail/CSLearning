package alg.leetcode;

/**
 * 整数反转
 *
 * @author zhangxq
 * @link https://leetcode.cn/problems/reverse-integer/
 * @since 2022/12/9
 */
public class P7ReverseInteger {
    
    /**
     * reverse integer
     * 尾数为0的数字，反转后消除0。例如：1920 -> 291
     * 负数保留符号。李荣：-123 -> -321
     * 超出范围返回0。超出Integer规定的范围[-2^31, 2^31-1]，反转后返回0。
     * <p>
     * x % 10 = x的最后一位数
     * x / 10 = x取余10后剩下的数字
     * 1. n作为转换后的数字，初始化为0
     * 2. 每次循环取出x的最后一个数字，例如123取出3
     * 3. n = n*10 + x%10。
     * n=0时，计算得出0*10+123%10=3；
     * n=2时，计算得出3*10+12%10=32；
     * n=1时，计算得出32*10+1%10=321；
     * 当x不等于0的时候就继续计算，当x小于10的时候，x/10=0
     * 由于全部都是数字计算，所以符号保留
     */
    public int reverse(int x) {
        long n = 0;
        while (x != 0) {
            n = n * 10 + x % 10;
            x = x / 10;
        }
        return (int) n == n ? (int) n : 0;
    }
    
}
