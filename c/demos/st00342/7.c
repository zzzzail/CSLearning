#include <stdio.h>
#include <math.h>

int main() {
    for (int n = 100; n <= 999; n ++) {
        int n1 = n % 10;
        int n2 = ( n / 10 ) % 10;
        int n3 = ( n / 100 ) % 10;
        int r = pow(n1, 3) + pow(n2, 3) + pow(n3, 3);
        if (n == r) printf("%d是水仙花数\n", n);
    }

    return 0;
}