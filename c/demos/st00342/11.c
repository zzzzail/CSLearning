#include <stdio.h>

int fun(int j);
int main() {

    for (int i = 1; i <= 3; i++) {
        printf("%d\n", fun(10));
    }

    return 0;
}

int fun(int j) {
    static int k;
    return (++k + j);
}