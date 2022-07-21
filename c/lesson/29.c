#include <stdio.h>

int main() {

    // 三目运算符
    // E1 ? E2 : E3;
    // 如果E1值不为0时执行E2，否则为0则执行E3
    printf("%d\n", 0 ? 100 : 200);
    printf("%d\n", -11 ? 100 : 200);

    return 0;
}