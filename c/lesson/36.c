#include <stdio.h>
#include <string.h>

int main() {

    char str[80];
    printf("请输入字符串（小于80个字符）\n");
    scanf("%s", str);
    printf("%s\n", str);

    char str2[80];
    for ( int i = strlen(str) - 1, j = 0; i >= 0; i --, j ++ ) {
        str2[i] = str[j];
    }
    printf("%s\n", str2);

    return 0;
}