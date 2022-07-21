#include <stdio.h>

typedef struct {
    int a;
    int b;
    int c;
} R;

int main() {

    printf("请输入线程数量!\n");
    int count;
    scanf("%d", &count);
    printf("线程数量为%d个。\n", count);
    printf("请输入每个线程需要的A类、B类、C类资源的数量！\n");
    int a, b, c;
    scanf("%d %d %d", &a, &b, &c);
    printf("A类资源需要%d个，B类资源需要%d个，C类资源需要%d个\n", a, b, c);

    // 最大需求矩阵
    R Max[count];
    // 已分配矩阵
    R Allocation[count];
    // 还需矩阵
    R Need[count];
    for (int i = 0; i < count; i++) {
        // 初始化
        R rm = {a, b, c};
        Max[i] = rm;
        R ra = {0, 0, 0};
        Allocation[i] = ra;
        Need[i] = rm;
    }

    while (1) {
        printf("是否继续分配资源？是请输入y，否请输入n。\n");
        char yes;
        scanf("%c", &yes);
        if (yes == 'n') { break; }

        printf("请输入为第几个线程分配资源！\n");
        int pn;
        scanf("%d", &pn);
        printf("请输入为第%d个线程分配A类、B类、C类资源的数量！\n", pn);
        int a1, b1, c1;
        scanf("%d %d %d", &a1, &b1, &c1);
        printf("为第%d个线程分配A类资源%d个，B类资源%d个，C类资源%d个\n", pn, a1, b1, c1);
    }

    return 0;
}