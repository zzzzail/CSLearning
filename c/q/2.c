#include <stdio.h>
#define N 5

int main() {

    int i, j, a[N][N], sum1 = 0, sum2 = 0;
    for (i = 0; i < N; i++) {
        for ( j = 0; j < N; j++ ) {
            scanf("%d", &a[i][j]);
        }
    }

    for (i = 0; i < N; i++) {
        sum1 += a[i][i];
        sum2 += a[i][N - 1 - i];
    }

    printf("sum1=%d,sum2=%d\n", sum1, sum2);

    return 0;
}