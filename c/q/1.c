#include <stdio.h>

int main() {

    // 打印一个数字金字塔
    for (int i = 1; i <= 9; i++) {
        for (int j = 0; j < 10 - i; j++) { printf(" "); }
        for (int j = 1; j <= i; j++) { printf("%d", j); }
        for (int j = i - 1; j >= 1; j--) { printf("%d", j); }
        printf("\n");
    }

    return 0;
}