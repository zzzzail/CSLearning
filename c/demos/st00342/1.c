#include <stdio.h>

int main() {

    int x = 8;

    do {
        printf("*\n");
        x--;
        x--;
    }
    while (x >= 0);

    return 0;
}