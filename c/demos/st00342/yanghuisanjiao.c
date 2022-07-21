#include <stdio.h>

int main() {
    int m, n, a[10][10];
    for (m = 0; m < 10; m ++) {
        a[m][0] = a[m][m] = 1; // 每一行的首个数字和最后一个数字都是1
        // 从第3行开始，第2个到n-1数，等于上面1行的第n-1 + 上面1行的第n个数字只和。
        for (n = 1; n < m; n ++) {
            a[m][n] = a[m - 1][n - 1] + a[m - 1][n];
        }
    }
    for (m = 0; m < 10; m ++) {
        for (n = 0; n <= m; n ++) {
            printf("%4d", a[m][n]);
        }
        printf("\n");
    }
    
    return 0;
}