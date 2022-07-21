#include <stdio.h>

int main() {
    int a, i, m;
    for (a = 1; a <= 1000; a++) {
        m = 0;
        for (i = 1; i <= a / 2; i++) {
            if (a % i == 0) m += i;
        }
        if (a == m) {
            printf("%d\n", a);
        }
    }


    int e = 0, n = 9;
    for (int i = 0; i < 5; i++) {
        for (int j = 0; j < e; j++) printf(" ");
        for (int j = 0; j < n; j++) printf("&");
        printf("\n");
        e += 1;
        n -= 2;
    }
    return 0;
}