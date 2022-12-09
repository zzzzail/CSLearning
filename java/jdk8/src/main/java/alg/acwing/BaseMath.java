package alg.acwing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

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
                System.out.println(i + " " + s);
            }
        }
        if (x > 1) System.out.println(x + " " + 1);
    }
    
    /**
     * 朴素筛法求素数
     */
    public static void getPrimes1(int n) {
        // primes 数组存储所有素数
        int[] primes = new int[n + 1];
        int cnt = 0;
        // st[x] 存储 x 是否被筛掉
        boolean[] st = new boolean[n + 1];
        for (int i = 2; i <= n; i++) {
            if (st[i]) continue;
            primes[cnt++] = i;
            for (int j = i + 1; j <= n; j += i) {
                st[j] = true;
            }
        }
        System.out.println(Arrays.toString(primes));
    }
    
    // 线性筛法求素数
    public static void getPrimes2(int n) {
        int cnt = 0;
        // primes 存储所有素数
        List<Integer> primes = new ArrayList<>();
        List<Boolean> st = new ArrayList<>();
        for (int i = 2; i <= n; i++) {
            if (!st.get(i)) {
                primes.set(cnt++, i);
            }
            for (int j = 0; primes.get(j) <= n / i; j++) {
                st.set(primes.get(j) * i, true);
                if (i % primes.get(j) == 0) break;
            }
        }
    }
    
    // 试除法求所有约数
    public static List<Integer> getDivisors(int x) {
        List<Integer> res = new ArrayList<>();
        for (int i = 1; i <= x / i; i++) {
            if (x % i == 0) {
                res.add(i);
                if (i != x / i) res.add(x / i);
            }
        }
        Collections.sort(res);
        return res;
    }
    
    // 欧几里得算法
    public static int gcd(int a, int b) {
        return b != 0 ? gcd(b, a % b) : a;
    }
    
    // 求欧拉函数
    public static int phi(int x) {
        int res = x;
        for (int i = 2; i <= x / i; i++)
            if (x % i == 0) {
                res = res / i * (i - 1);
                while (x % i == 0) x /= i;
            }
        if (x > 1) res = res / x * (x - 1);
        return res;
    }
    
    // 筛法求欧拉函数
    public static void getEulers() {
        int n = 100, N = 100;
        int cnt = 0;
        int[] primes = new int[N];      // primes[]存储所有素数
        int[] euler = new int[N];       // 存储每个数的欧拉函数
        boolean[] st = new boolean[N];  // st[x]存储x是否被筛掉
        
        euler[1] = 1;
        for (int i = 2; i <= n; i++) {
            if (!st[i]) {
                primes[cnt++] = i;
                euler[i] = i - 1;
            }
            for (int j = 0; primes[j] <= n / i; j++) {
                int t = primes[j] * i;
                st[t] = true;
                if (i % primes[j] == 0) {
                    euler[t] = euler[i] * primes[j];
                    break;
                }
                euler[t] = euler[i] * (primes[j] - 1);
            }
        }
    }
    
    // 快速幂
    public static int qmi(int m, int k, int p) {
        // 求 m^k mod p，时间复杂度 O(logk)。
        int res = 1 % p, t = m;
        while (k != 0) {
            if ((k & 1) != 0) res = res * t % p;
            t = t * t % p;
            k >>= 1;
        }
        return res;
    }
    
    // 扩展欧几里得算法
    int exgcd(int a, int b, int x, int y) {
        // 求x, y，使得ax + by = gcd(a, b)
        if (b == 0) {
            x = 1;
            y = 0;
            return a;
        }
        int d = exgcd(b, a % b, y, x);
        y -= (a / b) * x;
        return d;
    }
    
    // 高斯消元
    /*
    public static int gauss() {
        int n = 100;
        int c, r;
        for (c = 0, r = 0; c < n; c++) {
            int t = r;
            // 找到绝对值最大的行
            for (int i = r; i < n; i++)
                if (fabs(a[i][c]) > fabs(a[t][c]))
                    t = i;
            
            if (fabs(a[t][c]) < eps) continue;
            
            // 将绝对值最大的行换到最顶端
            for (int i = c; i <= n; i++) swap(a[t][i], a[r][i]);
            // 将当前行的首位变成1
            for (int i = n; i >= c; i--) a[r][i] /= a[r][c];
            // 用当前行将下面所有的列消成0
            for (int i = r + 1; i < n; i++)
                if (fabs(a[i][c]) > eps)
                    for (int j = n; j >= c; j--)
                        a[i][j] -= a[r][j] * a[i][c];
            r++;
        }
        
        if (r < n) {
            for (int i = r; i < n; i++)
                if (fabs(a[i][n]) > eps)
                    return 2; // 无解
            return 1; // 有无穷多组解
        }
        
        for (int i = n - 1; i >= 0; i--)
            for (int j = i + 1; j < n; j++)
                a[i][n] -= a[i][j] * a[j][n];
        
        return 0; // 有唯一解
    }
    */
    
    // 递推法求组合数
    /*
    public static void a() {
        // c[a][b] 表示从a个苹果中选b个的方案数
        for (int i = 0; i < N; i ++ )
            for (int j = 0; j <= i; j ++ )
                if (!j) c[i][j] = 1;
                else c[i][j] = (c[i - 1][j] + c[i - 1][j - 1]) % mod;
    }
    */
    
}
