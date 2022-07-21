#include <stdio.h>

void LineMax(int x[3][4]) {
    int i,j,p;
    for (i = 0; i < 3; i ++) {
        p = 0;
        for (j = 0; j < 4; j ++)
            if (x[i][p] < x[i][j]) p = j;
        printf("The max value in line %d is %d\n", i + 1, x[i][p]);
    }
}
int main () {

    int x[3][4] = { {1,5,7,4}, {2,6,4,3}, {8,2,3,1} };
    for (int i = 0; i < 3; i ++) {
        for (int j = 0; j < 4; j ++) {
            printf("%d ", x[i][j]);
        }
        printf("\n");
    }
    LineMax(x);

    return 0;
}