#include <stdio.h>
#include <stdlib.h>

int main() {
    char c[] = {0, 1, 2, 3, 4, 5};
    char *p1 = c;
    printf("p1=%p\n", p1);
    printf("p1+1=%p\n", p1 + 1);
    printf("*(p1+1)=%d\n", *(p1 + 1));

    int arr[] = {0, 1, 2, 3, 4, 5};
    int *p2 = arr;
    printf("p2=%p\n", p2);
    // 这里的p2+1，是加上p2类型的字节长度，int是4个字节，所以加的是4
    printf("p2+1=%p\n", p2 + 1);

    char *str = (char*) malloc(10 * sizeof(char));
    printf("%p\n", str);
    printf("%lu", sizeof(str));
    free(str);

    return 0;
}