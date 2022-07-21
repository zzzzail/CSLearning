#include <stdio.h>
#include <stdlib.h>
#include <time.h>

int main() {
    // 随机种子
    srand(time(0));
    int a = rand() % 100;
    printf("%d\n", a);

    printf("猜一个100以内的数，猜中后结束。\n");
    int number = 0;
    do {
        scanf("%d", &number);
        if (number > a) {
            printf("你猜的数大了\n");
        }
        else if (number < a) {
            printf("你猜的数小了\n");
        }
    }
    while (number != a);

    printf("恭喜你猜对了");

    return 0;
}