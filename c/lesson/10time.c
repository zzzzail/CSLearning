#include <stdio.h>

/**
 * 计算时间
 */
int main(int argc, char const *argv[])
{
    int hour1, minute1, hour2, minute2;
    printf("请输入[小时 分钟]");
    scanf("%d %d", &hour1, &minute1);
    printf("请输入[小时 分钟]");
    scanf("%d %d", &hour2, &minute2);

    // 计算第一个时间到第二个时间之间差多少
    int hourResult, minuteResult;
    minuteResult = (hour2 * 60 + minute2) - (hour1 * 60 + minute1);
    hourResult = minuteResult / 60;
    minuteResult = minuteResult % 60;
    printf("两个时间相差%d时%d分\n", hourResult, minuteResult);

    return 0;
}
