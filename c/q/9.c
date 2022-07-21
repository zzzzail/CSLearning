#include <stdio.h>
#include <string.h>

int f(int n);

int main() {

    // int a, b;
    // a = (1, 2);
    // printf("%d\n", (a = 2) && (b = -2));

    // int arr1[][2] = {0, 1, 2, 3};

    // char s[3], *p = s;
    // p = "12";
    // printf("%s\n", p);

    // char *p2 = "abc\0def";
    // printf("%lu\n", sizeof(p2));

    // printf("%d\n", 10 << (3 + 1));

    // char c = '1';
    // putchar(c);

    // printf("%d\n", f(5));

    char *s1 = "first", *s2 = "second", s[] = "program";
    strcpy(s + 4, s2 + 2);
    strcpy(s + 6, s1 + 1);
    puts(s);

    return 0;
}

int f(int n) {
    if (n == 1) return 1;
    else return n*n + f(n - 1);
}