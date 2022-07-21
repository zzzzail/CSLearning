#include <stdio.h>

// 从键盘输入一个十进制数字，按二进制位输出该数字。
int main() {
    int num;
    scanf("%d", &num);
    printf("输入的数字是%d\n", num);

    // 构造一个第一位为1，其他位全部位0的二进制数
    int mask = 1 << ( sizeof(num) * 8 - 1);
    // printf("%d", mask);
    for (int i = 1; i <= sizeof(int) * 8; i ++) {
        // 按位与操作，若值为0，说明num二进制第一位为0，否则为1
        // 输出最高位的值
        putchar(num & mask ? '1' : '0'); 
        num = num << 1; // 将数字右移一位
        if (i % 4 == 0) putchar(','); // 每隔四位之后插入一个逗号
    }

    printf("\bB\n");

    return 0;
}