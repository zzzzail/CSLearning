#include <stdio.h>
#include <stdlib.h>
#include "SymmetricalMatrix.h"

DataType SMatrixGet(DataType sm[], int i, int j) {
    // 判断边界
    if (i < 0 || j < 0) {
        printf("matrix underflow");
        exit(0);
    }
    if (i >= SMatrixScala || j >= SMatrixScala) {
        printf("matrix overflow");
        exit(0);
    }
    int k;
    if (i >= j) { // 在下三角矩阵
        k = i * (i + 1) / 2 + j;
    } else {  // 在上三角矩阵
        k = j * (j + 1) / 2 + i;
    }
    return sm[k];
}

void SMatrixMultiply(DataType a[], DataType b[], DataType r[SMatrixScala][SMatrixScala]) {
    int i, j, k;
    for (i = 0; i < SMatrixScala; i++) {
        for (j = 0; j < SMatrixScala; j++) {
            r[i][j] = 0; // 初始化r
            for (k = 0; k < SMatrixScala; k++) {
                r[i][j] += SMatrixGet(a, i, k) * SMatrixGet(b, k, j);
            }
        }
    }
}

void SMatrixPrint(int m[SMatrixScala][SMatrixScala]) {
    int i, j;
    printf("[\n");
    for (i = 0; i < SMatrixScala; i++) {
        printf("\t[");
        for (j = 0; j < SMatrixScala - 1; j++) {
            printf("%d, ", m[i][j]);
        }
        printf("%d", m[i][SMatrixScala - 1]);
        printf("]\n");
    }
    printf("]\n");
}

void SMatrixMultiplyTest() {
    int a[] = {1,
               2, 3,
               4, 5, 6};
    int b[] = {9,
               5, 8,
               2, 7, 1};
    int r[SMatrixScala][SMatrixScala]; // r没有初始化
    SMatrixMultiply(a, b, r);
    SMatrixPrint(r);
}