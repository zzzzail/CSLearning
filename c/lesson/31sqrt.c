#include <stdio.h>
#include <math.h>

int main () {

    double a = sqrt(100);
    printf("%lf\n", a);

    // 10^2
    printf("%lf\n", pow(10, 2));

    // square root 平方根
    // 计算两点之间的距离，(x1, x2), (y1, y2)
    int x1 = 10, x2 = 10, y1 = 8, y2 = 0;
    double distance = sqrt(pow((x1 - y1), 2) + pow((x2 - y2), 2));
    printf("%lf\n", distance);

    return 0;
}