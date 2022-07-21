#include <stdio.h>
#include "Matrix.h"

// 两个矩阵相乘
void MatrixMultiply(int a[MatrixScala][MatrixScala], int b[MatrixScala][MatrixScala], int r[MatrixScala][MatrixScala]) {
    int i, j, k;
    for (i = 0; i < MatrixScala; i++) {
        for (j = 0; j < MatrixScala; j++) {
            r[i][j] = 0;
            for (k = 0; k < MatrixScala; k++) {
                r[i][j] += a[i][k] * b[k][j];
            }
        }
    }
}

void MatrixPrint(int m[MatrixScala][MatrixScala]) {
    int i, j;
    printf("[\n");
    for (i = 0; i < MatrixScala; i++) {
        printf("\t[");
        for (j = 0; j < MatrixScala - 1; j++) {
            printf("%d, ", m[i][j]);
        }
        printf("%d", m[i][MatrixScala - 1]);
        printf("]\n");
    }
    printf("]\n");
}

void MatrixMultiplyTest() {
#define MatrixScala 3
    int a[MatrixScala][MatrixScala] = {1, 2, 3,
                                       4, 2, 1,
                                       3, 5, 7};
    int b[MatrixScala][MatrixScala] = {3, 5, 2,
                                       4, 1, 7,
                                       6, 2, 9};
    int r[MatrixScala][MatrixScala]; // r没有初始化
    MatrixPrint(a);
    MatrixPrint(b);
    MatrixPrint(r);
    MatrixMultiply(a, b, r);
    MatrixPrint(r);
}

void TransMatrix(int a[MatrixScala][MatrixScala], int b[MatrixScala][MatrixScala]) {
    for (int i = 0; i < MatrixScala; i++) {
        for (int j = 0; j < MatrixScala; j++) {
            b[j][i] = a[i][j];
        }
    }
}

void TransMatrixTest() {
    int a[MatrixScala][MatrixScala] = {1, 2, 3,
                                       4, 2, 1,
                                       3, 5, 7};
    int b[MatrixScala][MatrixScala];
    TransMatrix(a, b);
    MatrixPrint(b);
}

