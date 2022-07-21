#include <stdio.h>

int main() {

    int a = 10;
    int b = 20;
    // 交换a，b两个变量的值
    int temp = a;
    a = b;
    b = temp;
    printf("a=%d;b=%d;", a, b);

    return 0;
}