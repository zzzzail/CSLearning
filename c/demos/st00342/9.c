#include <stdio.h>

int main() {
    char s[] = "123\0";
    int len = 0, i;
    while (s[len] != '\0') {
        len ++;
    }

    for ( i = len - 1; i >= 0; i -- ) {
        printf("%c", s[i]);
    }
    printf("\n");
    
    return 0;
}