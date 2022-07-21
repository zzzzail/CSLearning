#include <stdio.h>
#include <string.h>
#include <math.h>

int main() {

    char *s1 = "first", *s2 = "second", s[20] = "program";
    strcpy(s + 4, s2 + 2);
    strcpy(s + 6, s1 + 1);
    puts(s);

    int a = pow(10, 2);
    printf("%d\n", a);

    return 0;
}