#include <stdio.h>
#include <stdlib.h>

void sort(int arr[], int n);

int main() {

    int arr[5] = {1, 2, 6, 5, 4};
    sort(arr, 5);
    for (int i = 0; i < 5; i ++) {
        printf("%d", arr[i]);
    }
    printf("\n");

    return 0;
}

void sort(int arr[], int n) {
    for (int i = n - 1; i >= 0; i--) {
        for (int j = n - 1; j >= i + 1; j--) {
            if (arr[j] < arr[j - 1]) {
                int t = arr[j];
                arr[j] = arr[j - 1];
                arr[j - 1] = t;
            }
        }
    }
}