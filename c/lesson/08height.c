#include <stdio.h>

int main() {
    float a, b;

    printf("请输入多少尺");
    scanf("%f", &a);
    printf("请输入多少寸");
    scanf("%f", &b);
    // 当两个整数做运算的时候，结果仍然是整数
    // 浮点数和整数放在一起运算时，C会将整数转换成浮点数，然后在进浮点数的运算。
    printf("你的身高是%f米\n", ((a + b / 12.0) * 0.3048));

    return 0;
}