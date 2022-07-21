#include <stdio.h>

typedef long long LL;

int main() {
    
    int a = 100;
    int *ap = &a;
    printf("%d\n", a);
    printf("%p\n", ap);
    printf("%d\n", *ap);

    char *str1 = "str1";
    printf("%s\n", str1);

    LL lnum = 999999L;
    printf("%d", lnum);


    return 0;
}