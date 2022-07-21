#include <stdio.h>

int *fun(int *a, int *b) {
    return (*a > *b) ? a : b;
}
int main() {

    int p = 2, q = 4, *r;
    r = fun(&p, &q);
    printf("%d\n", *r);

    int a[3][3] = {1, 2, 3, 4, 999, 6, 7, 8, 9};
    printf("%d\n", *(*(a + 1) + 1));

    return 0;
}