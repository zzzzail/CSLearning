#include <stdio.h>

int main() {

    char str[10];
    // 获取控制台输入的字符串
    gets(str);
    // 把字符串打印到控制台
    puts(str);

    int num = 0;

    for ( int i = 0; str[i] != '\0'; i ++ ) {
        if (str[i] >= '0' && str[i] <= '9') {
            continue;
        }
        num ++;
    }

    printf("非数字字符出现的次数是%d次\n", num);

    return 0;
}