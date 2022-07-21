#include <stdio.h>

int main() {

    int i, s = 0;
    for (i = 1;; i++) {
        if (s > 50)
            break;
        if (i % 2 == 0)
            s += i;
        printf("i = %d, s = %d\n", i, s);
    }

    // --------------------------------------------------------
    int a = 3, b = 2, c = 1, d;
    switch (a = b == c + 1) {
        case 1:
            d = 1;
            break;
        case 2:
            d = 2;
            break;
        case 3:
            d = 3;
            break;
        default:
            d = 4;
    }
    printf("d = %d\n", d);


    return 0;
}