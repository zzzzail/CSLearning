#include <stdio.h>

int main() {
    char a = 'j';
    printf("%c", a);

    // 把字母转换成大写
    printf("%c", a + 'A' - 'a');
    // 把字母转换成小写
    char b = 'G';
    printf("%c", b + 'a' - 'A');

    return 0;
}