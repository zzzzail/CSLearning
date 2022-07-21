#include <stdio.h>

/**
 * 指针
 * &叫做取地址符
 * *表示指针，指针变量存放的是内存的地址
 * 方法接收一个指针的参数，使用方法的时候传递一个地址值。
 * 
 * 例如：
 * int fun(int *p) { ... }
 * fun(&num);
 */
int main() {
    int i = 10;
    // &i 表示的是i数据存储的地址，64位的系统和32位的存储的地址大小不同。
    printf("%p\n", &i);

    int a = 10;
    printf("a=%d\n", a);
    // 指针类型
    int *b = &a;
    printf("a=%d\n", a);
    *b = 20;
    printf("a=%d\n", a);
    printf("b=%d\n", *b);

    return 0;
}