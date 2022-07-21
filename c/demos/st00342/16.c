#include <stdio.h>

int main() {

    int a = 1, b, c, d;
    b = ++a, c = b++;
    d = a + c;
    printf("%d", d);

    return 0;
}