#include <stdio.h>

int main() {
    int i = 1, s = 3;
    do {
        s += i++;
        if (s % 7 == 0) continue;
        else ++i;
    } 
    while (s < 15);

    printf("%d\n", i);

    return 0;
}