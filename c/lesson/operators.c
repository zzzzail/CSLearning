#include <stdio.h>

int main() {

    // ~ 按位取反
    int a1 = 0xFFFFFFFA;
    printf("%d\n", a1);
    // | 按位或
    int a2 = 10 | 2;
    printf("%d\n", a2);
    // & 按位与
    int a3 = 10 & 2;
    printf("%d\n", a3);
    // ^ 异或
    int a4 = 10 ^ 5;
    printf("%d\n", a4);

    int a5 = 10 >> 1;
    printf("%d\n", a5);
    int a6 = 10 << 1;
    printf("%d\n", a6);
    printf("sizeof(a6) = %lu\n", sizeof(a6));

    return 0;
}
