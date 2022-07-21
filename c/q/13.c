#include <stdio.h>
#include <math.h>

int main() {

    // 水仙花数
    for (int i = 100; i <= 999; i++) {
        int t, g, s, b;
        t = i;
        g = i % 10;
        t /= 10;
        s = t % 10;
        t /= 10;
        b = t % 10;
        if (i == ( pow(g, 3) + pow(s, 3) + pow(b, 3) )) { printf("%d\n", i); }
    }

    return 0;
}