#include <stdio.h>

/**
 * 冒泡排序
 * 
 * 核心思想：最后一个元素与前面一个元素比较，若最后一个元素小于前面的元素，则交换两者的位置。
 * 
 */
int main() {

    int arr[] = {10, 9, 8, 7, 6, 5, 4, 3, 2, 1};

    for (int i = 1; i <= 9; i ++) {
        for (int j = 9; j >= i; j --) {
            if (arr[j] < arr[j - 1]) {
                int t = arr[j];
                arr[j] = arr[j - 1];
                arr[j - 1] = t;
            }
        }
    }

    for (int i = 0; i < 10; i ++) {
        printf("%d ", arr[i]);
    }

    return 0;
}