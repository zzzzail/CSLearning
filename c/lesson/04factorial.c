#include <stdio.h>

int main(void) {

    // 计算10的阶乘
    int i = 2, t = 1;
    while (i <= 10) {
        t = i * t;
        i = i + 1;
    }
    // %d digital
    printf("%d\n", t);

    return 0;
}
