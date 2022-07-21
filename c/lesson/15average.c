#include <stdio.h>

/* 计算平均数 */
int main() {
    int num = 0;
    // 记录数量
    int count = 0;
    // 记录总数
    int all = 0;
    // 平均值
    float average = 0;
    printf("输入一些数字，计算平均值。\n");
    while (1) {
        scanf("%d", &num);
        // 101为结束条件
        if (num == 101) { break; }
        
        count ++;
        all += num;
        average = all / count;
        printf("现在的平均数为%f\n", average);
    }

    printf("记录个数%d，平均数%f\n", count, average);

    return 0;
}