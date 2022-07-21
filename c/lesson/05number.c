#include <stdio.h>

/**
 * %d d表示digital
 */
int main(int argc, char const *argv[]) {

    printf("21 + 56 = %d\n", 21 + 56);

    int a1 = 5E3; // aeb = a * 10 ^ b。 例如 314.15926e-2 就是 3.1415926

    printf("%d\n", a1);
    int a2 = 100; // 十进制
    int a3 = 0777; // 八进制
    int a4 = 0XFFFfff; // 十六进制

    short int a5 = 100;
    int a6 = 100;
    long int a7 = 100;
    long long int a8 = 100;

    float a9 = 1.002; // 单精度小数
    double a10 = 29.778; // 双精度小数

    return 0;
}
