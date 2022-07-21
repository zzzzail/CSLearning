#include <stdio.h>

/**
 * C语言的基础类型
 * 整数：char、short、int、long、long long
 * 浮点数：float、double、long double
 * 逻辑：bool
 * 指针：
 * 自定义类型：
 * 
 * 所表达的数的范围：char < short < int < float < double
 * char占1个字节
 * short占2个字节
 * int占4个字节
 * long占8个字节
 * long long占8个字节
 * float占4个字节
 * double占8个字节
 * long double占16个字节
 * 
 * 浮点数：
 * float  32位 范围是+-(1.2*10^-38~3.4*10^38)，0，+-inf，nan
 * double 64位 范围是+-(2.2*10^-308~1.79*10^308)，0，+-inf，nan
 */
int main() {
    printf("char占%lu个字节\n", sizeof(char)); // 1
    printf("short占%lu个字节\n", sizeof(short)); // 2
    printf("int占%lu个字节\n", sizeof(int)); // 4
    printf("long占%lu个字节\n", sizeof(long)); // 8
    printf("long long占%lu个字节\n", sizeof(long long)); // 8
    printf("float占%lu个字节\n", sizeof(float)); // 4
    printf("double占%lu个字节\n", sizeof(double)); // 8
    printf("long double占%lu个字节\n", sizeof(long double)); // 16


    // unsigned
    unsigned char a = 255;
    printf("%d\n", a);
    // 负数用补码表示
    char b = -1;
    printf("%d\n", (unsigned char) b);

    // 0开头的表示八进制的数字
    int c = 017; // 001111 => 15
    printf("%d\n", c);
    // 0x开头的表示一个十六进制的数字
    int d = 0x8A; // 1000 1010 => 138
    printf("%d\n", d);

    // double 双精度浮点数
    double e = 12345.67890;
    printf("%e\n", e); // 科学计数法
    printf("%f\n", e); // 实际数值

    double f = 1.23456789E-10;
    printf("%.32f\n", f);

    // 指数形式表示，相当于3.456 * 10^3
    int g = 3.456E3;
    printf("%d\n", g);

    return 0;
}