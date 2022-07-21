#include <stdio.h>

int fun(int a[], int n);
int main() {

    int x[7] = {1, 2, 3, 4, 5, 6, 7}, s;
    s = fun(x, 7);
    printf("%d\n", s);

    return 0;
}
int fun(int a[], int n) {
    if (n >= 1) return fun(a, n - 1) + a[n - 1];
    else return 0;
}