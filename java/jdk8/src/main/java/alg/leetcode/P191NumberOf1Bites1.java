package alg.leetcode;

/**
 * @link https://leetcode.cn/problems/number-of-1-bits/
 * 位 1 的个数
 */
public class P191NumberOf1Bites1 {
    
    public static void main(String[] args) {
    
    
    }
    
    // you need to treat n as an unsigned value
    public int hammingWeight(int n) {
        int res = 0;
        for (int i = 0; i < 32; i++) {
            if (((n >> i) & 1) == 1) ++res;
        }
        return res;
    }
    
    public int hammingWeight2(int n) {
        int count = 0;
        while (n != 0) {
            count += n & 1;
            n >>>= 1;
        }
        return count;
    }
}
