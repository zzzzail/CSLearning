#include <stdio.h>
#include <math.h>

unsigned int getLength(unsigned int num);

/**
 * 判断数字是否是回文数
 */
unsigned int isHuiWenShu(unsigned int num);

int main() {

    unsigned int num;
    scanf("%ld", &num);
    if (isHuiWenShu(num)) {
        printf("%ld 是回文数\n", num);
    }
    else {
        printf("%ld 不是回文数\n", num);
    }

    return 0;
}

unsigned int getLength(unsigned int num) {
    // 如果是0～9则直接返回1
    if (num <= 9) return 1;

    unsigned int length;
    while (1) {
        unsigned int p = num / pow(10, length);
        if (p >= 1) length ++;
        else return length;
    }
}

unsigned int isHuiWenShu(unsigned int num) {
    // n 保存键盘输入的正整数
    unsigned int n;
    // left 保存正整数最左边的数字
    // right 保存正整数最右边的数字
    // i 保存正整数的位数
    // flag 记录左右两侧数字是否相等
    unsigned int left, right, i, flag;
    i = getLength(num);
    n = num;
    flag = 0;
    while (i >= 2 && flag == 0) {
        // 将数字最左边的值赋值给left
        left = n / (int) pow(10, i - 1);
        // 将数字最右边的值赋值给right
        right = n % 10;
        // 222 % 100 = 22; 22 / 10 = 2; 就是把n最左边和最右边的数字去掉
        n = n % (int) pow(10, i - 1) / 10;
        i -= 2;
        // 若正整数n当前最左边和最右边的数字不相等，则不是回文数
        if (left != right) flag = 1; 
    }
    
    // 若没有出现左右两边的数字不相等的情况，则是回文数，返回1
    if (flag == 0) return 1;
    // 若不是回文数，则返回0
    else return 0;
}