#include <stdio.h>

int main() {

    double foot, inch;
    printf("请输入多少尺");
    scanf("%lf", &foot);
    printf("请输入多少寸");
    scanf("%lf", &inch);
    printf("你的身高是%lf米", (foot + inch / 12.0) * 0.3048);

    return 0;
}