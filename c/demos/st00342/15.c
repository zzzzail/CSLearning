#include <stdio.h>

int main() {

    // 求两个数的最大公约数和最小公倍数
    int a = 30, b = 15, t, r, n;
    // scanf("%d %d", a, b);
    // 始终保持a大与等于b
    if (a < b) {
        t = a;
        a = b;
        b = t;
    }
    r = a % b;
    n = a * b;
    while (r != 0) {
        a = b;
        b = r;
        r = a % b;
    }
    printf("这两个数的最大公约数是%d，最小公倍数是%d\n", b, n / b);

    // printf("%d", 10 << 3 + 1);
    // putchar('a');

    return 0;
}