package alg.leetcode;

/**
 * @link https://leetcode.cn/problems/reverse-bits/
 * 颠倒二进制位
 */
public class P190ReverseBits1 {
    
    public static void main(String[] args) {
    
    }
    // you need treat n as an unsigned value
    public int reverseBits(int n) {
        int res = 0;
        for (int i = 0; i < 32; ++i) {
            if ((1 & (n >> i)) == 1) res = res + 1;
            res = res << 1;
        }
        return res;
    }
    
    public int reverseBits2(int n) {
        int res = 0;
        for (int i = 0; i < 32; ++i) {
            res |= (n & 1) << (31 - i);
            n >>>= 1;
        }
        return res;
    }
}
