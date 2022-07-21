#include <stdio.h>

int main() {
    int *q, i;
    int a[10] = {10, 20, 30, 40, 50, 60, 70, 80, 90, 100};
    q = &a[1];
    printf("1-%p\n", q);
    for (i = 0; i < 5; i ++, q ++) {
        printf("2-%p\n", q);
        printf("%d\n", *q);
        printf("%d\n", *(q++));
        printf("3-%p\n", q);
    }
    return 0;
}