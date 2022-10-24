package com.java.dsa.acwing;

/**
 * @author zhangxq
 * @since 2022/10/24
 */
public class BaseMath {
    
    /**
     * 判断 x 是否是质数
     * 质数是大于 1 的自然数中，除了 1 和它本身以外没有其他因数的自然数。
     * 即除了 1 和它本身，不存在一个数，它除以这个数的结果是一个自然数。
     */
    public static boolean isPrime(int x) {
        if (x <= 1) return false;
        for (int i = 2; i <= x; i++) {
            if (x % i == 0) {
                return false;
            }
        }
        return true;
    }
    
    
}
