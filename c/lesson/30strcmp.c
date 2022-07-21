#include <stdio.h>
#include <string.h>

int main() {
    // 比较两个字符串大小，如果等于0则说明两个字符串相等，否则不想等。
    int a = strcmp("abc", "abcd");
    printf("%d\n", a);

    return 0;
}