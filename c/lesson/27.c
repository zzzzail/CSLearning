#include <stdio.h>

int main() {

    short a = 0x0F;
    // 整数用补码表示
    short b = -0x0F; // (0000 1111)原 = (1111 0001)补 = 0xF1
    printf("a>>1=%x\n", a >> 1); // 0000 0111 = 0x07
    // 负数右移操作时，左侧空位补1
    printf("b>>1=%x\n", b >> 1); // 1111 1000 = 0xF8

    unsigned short c = 0x0F;
    // 声明一个无符号数字类型时，若赋值时带有符号，则把带符号数的原码转换成补码表示。
    // (0000 1111)原 = (1111 0001)补
    unsigned short d = -0x0F;
    printf("c=%d;d=%d;\n", c, d);
    // 无符号数右移全部，空位添0
    printf("c>>1=%d\n", c >> 1);
    printf("d>>1=%d\n", d >> 1);

    return 0;
}