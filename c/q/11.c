#include <stdio.h>
#include <string.h>

int main() {

    char s1[80] = {"1234567890abc"};
    // gets(s1);
    // printf("%s\n", s1);
    
    // 小心下标越界！
    char s2[80];
    for (int i = strlen(s1) - 1, j = 0; i >= 0; i--, j++) {
        s2[j] = s1[i];
    }

    printf("%s\n", s1);
    printf("%s\n", s2);

    return 0;
}