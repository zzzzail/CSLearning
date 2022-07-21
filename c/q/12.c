#include <stdio.h>

int main() {

    for (int i = 0; i <= 5; i++) {
        for (int k = 0; k <= i; k++) {
            printf("%c", ' ');
        }
        for (int j = 0; j <= 10; j++) {
            printf("%c", '*');
        }
        printf("\n");
    }

    return 0;
}