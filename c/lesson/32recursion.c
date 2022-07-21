#include <stdio.h>

float f(long n);

int main() {

    float a = f(10);
    printf("%f\n", a);

    return 0;
}

/**
 * 使用递归的方式计算n的阶乘
 * n! = n * (n - 1) * (n - 2) * ... * 2 * 1;
 * 
 * n! = 1                0, 1
 *      n * (n - 1)!     n > 1
 * 所以n的阶乘递归结束条件是 n <= 1
 */
float f(long n) {
    // 结束递归的条件
    if (n <= 1) { return 1.0; }

    return n * f(n - 1);
}