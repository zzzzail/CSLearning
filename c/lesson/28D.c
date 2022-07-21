#include <stdio.h>

// 输入一个数字，把该数字转换成二进制输出
int main() {
    int num, i;
    printf("请输入一个数字\n");
    scanf("%d", &num);
    printf("您输入的是%d\n", num);

    // 构造一个首位为1，剩下的所有位为0的二进制数
    int mask = 1 << ( sizeof(int) * 8 - 1 );

    for (i = 1; i <= sizeof(int) * 8; i ++) {
        // 打印出num第一位二进制数
        putchar( num & mask ? '1' : '0');
        // num左移一位
        num = num << 1;
        // 每四位打印一个空格
        if ( i % 4 == 0 ) putchar(' ');
    }

    printf("\bB\n");

    return 0;
}