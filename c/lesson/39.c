#include <stdio.h>
#include <ctype.h>
#include <math.h>

unsigned int getLength(unsigned int sum);

int main() {

    printf("%d\n", 999 / 1000);
    printf("%d\n", getLength(999));
    printf("%d\n", getLength(100));

    return 0;
}

unsigned int getLength(unsigned int sum) {
    // 处理 0 ～ 9 的情况
    if (sum <= 9) return 1;
    
    unsigned int length = 1;
    while (1) {
        unsigned int p = sum / pow(10, length);
        if (p >= 1) length ++ ;
        else return length;
    }
}