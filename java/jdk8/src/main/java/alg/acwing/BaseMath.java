package alg.acwing;

import java.util.Arrays;

/**
 * @author zhangxq
 * @since 2022/10/24
 */
public class BaseMath {
    
    public static void main(String[] args) {
        dividePrime(8);
    }
    
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
    
    /**
     * 分解质因数
     * 每个合数都可以写成几个质数相乘的形式，其中每个质数都是这个合数的因数，把一个
     * 合数用质因数相乘的形式表示出来，叫做分解质因数。如30=2×3×5 。分解质因数只
     * 针对合数。
     */
    public static void dividePrime(int x) {
        for (int i = 2; i <= x; i++) {
            if (x % i == 0) {
                int s = 0;
                while (x % i == 0) {
                    x /= i;
                    s++;
                }
                System.out.println( i + " " + s);
            }
        }
        if (x > 1) System.out.println(x + " " + 1);
    }
    
    /**
     * 朴素筛法求素数
     */
    public void getPrimes(int n) {
        // primes 数组存储所有素数
        int[] primes = new int[n + 1];
        int cnt = 0;
        // st[x] 存储 x 是否被筛掉
        boolean[] st = new boolean[n + 1];
        for (int i = 2; i <= n; i++) {
            if (st[i]) continue;
            primes[cnt++] = i;
            for (int j = i + 1; j <= n; j+=i) {
                st[j] = true;
            }
        }
        System.out.println(Arrays.toString(primes));
    }
}
