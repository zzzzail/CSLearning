#include <stdio.h>

int main() {

    // 根据指针地址循环打印字符串
    char *str = "This is a string.";
    while (*str != '\0') {
        printf("%c", *str);
        str ++;
    }

    char s2[] = {"123"};
    printf("%s\n", s2);
    
    return 0;
}