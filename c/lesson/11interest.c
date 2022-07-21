#include <stdio.h>

int main() {
    int x;
    scanf("%d", &x);
    double r = x * ( (1 + 0.03) * (1 + 0.03) * (1 + 0.03) );
    printf("%lf", r);

    return 0;
}