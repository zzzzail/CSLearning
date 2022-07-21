#include <stdio.h>
#include <string.h>

int main() {
    int x, y = 4;
    for (x = 1; x <= 100; x++) {
        if (y >= 30) break;
        if (y % 3 == 1) {
            y += 3;
            continue;
        }
        y -= 2;
    }
    printf("%d\n", x);

    return 0;
}