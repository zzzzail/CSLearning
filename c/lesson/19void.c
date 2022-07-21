#include <stdio.h>
#include <stdbool.h>
#include <math.h>

/**
 * 判断数字是否为素数
 * 0 false, 1 true
 */
int isPrime(int num) {
    int ret = 1;
    for ( int i = 2; i < num - 1; i ++ ) {
        if (num % i == 0) {
            ret = 0;
            break;
        }
    }

    return ret;
}

int main() {

    int a = isPrime(6);
    printf("%d\n", a);

    bool f1 = true;
    printf("%d\n", f1); // 1 true
    bool f2 = false;
    printf("%d\n", f2); // 0 false

    // sqrt 根下
    printf("%lf", sqrt(4));

    return 0;
}

