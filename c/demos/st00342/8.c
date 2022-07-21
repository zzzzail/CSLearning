#include <stdio.h>

int main() {
    int a[3][3] = {1, 4, 17, 3, 6, 9, 2, 5, 8}, i, s = 0;
    for (i = 0; i <= 2; i++) {
        s = s + a[i][2-i];
    }
    printf("%d", s);
    return 0;
}