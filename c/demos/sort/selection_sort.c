#include <stdio.h>

/**
 * 选择排序
 * 核心思想：用第i个数组和范围在[i + 1, n - 1]位置中所有的数进行比较，
 * 找到一个在[i, n - 1]范围内最小的那个数字，并记下下标k。
 * 将第i位置上的元素和第k位置上的元素交换。
 */
int main() {

    int arr[] = {10, 9, 8, 7, 6, 5, 4, 3, 2, 1};

    for (int i = 0; i <= 9; i ++) {
        int k = i;
        for (int j = i + 1; j <= 9; j ++) {
            // k记录每轮比较中的最小者的下标
            if (arr[j] < arr[k]) k = j;
        }
        // 交换位置
        if (k != i) {
            int t = arr[i];
            arr[i] = arr[k];
            arr[k] = t;
        }
    }

    for (int i = 0; i < 10; i ++) {
        printf("%d ", arr[i]);
    }
    printf("\n");

    return 0;
}