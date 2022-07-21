#include <stdio.h>

unsigned int getFibonacci(unsigned int n);

int main() {

    unsigned int fibonacci4 = getFibonacci(4);
    printf("%d\n", fibonacci4);

    unsigned int fibonacci7 = getFibonacci(7);
    printf("%d\n", fibonacci7);

    unsigned int fibonacci17 = getFibonacci(17);
    printf("%d\n", fibonacci17);

    unsigned int fibonacci18 = getFibonacci(18);
    printf("%d\n", fibonacci18);

    unsigned int fibonacci25 = getFibonacci(25);
    printf("%d\n", fibonacci25);

    return 0;
}

unsigned int getFibonacci(unsigned int n) {
    if ( n == 0 || n == 1 || n == 2 ) return 1;
    else return getFibonacci(n - 1) + getFibonacci(n - 2);
}
