#include <stdio.h>

int a[10], i;
void fun1() {
    for (i = 0; i < 10; i ++)
        a[i] = i + 1;
}
void fun2() {
    int a[10], i, max = 5;
    for (i = 0; i < max; i ++)
        a[i] = i;
}
void fun3(int a[]) {
    int i;
    for (i = 0; i < 10; i ++) {
        printf("%d", a[i]);
    }
    printf("\n");
}
int main() {
    fun1();
    fun3(a);
    fun2();
    fun3(a);
    return 0;
}