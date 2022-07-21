#include <stdio.h>

int main(int argc, char const *argv[])
{
    const int AMOUNT = 100;

    int price = 0;
    printf("请输入金额（元）\n");
    scanf("%d", &price);

    int change = AMOUNT - price;
    printf("找您%d元\n", change);

    return 0;
}
