#include <stdio.h>

int main () {

    int arr[10] = {0};
    int *p1 = arr;
    printf("%p\n", p1);
    printf("%p\n", p1 + 1);
    printf("%p\n", (p1 + 1) - p1);

    return 0;
}