#include <stdio.h>
#include <string.h>

size_t mylen(const char* str) {
    int idx = 0;
    while(str[idx] != '\0') {
        idx++;
    }
    return idx;
}

int main() {
    char str[] = "Hello";
    printf("strlen=%lu\n", mylen(str));
    printf("sizeof=%lu\n", sizeof(str));


    char str2[] = "Hello2";
    // 比较两个字符串是否相等，0表示相等；1表示s1>s2；-1表示s1<s2
    int r = strcmp(str, str2);
    printf("%d", r);

    return 0;
}

