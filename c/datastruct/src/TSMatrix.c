#include <stdio.h>
#include <stdlib.h>
#include "TSMatrix.h"

void CreateTriTable(TSMatrix *b, int a[][5], int m, int n) {
    int i, j, k = 0;
    for (i = 0; i < m; i++) {
        for (j = 0; j < n; j++) {
            if (a[i][j] != 0) {             // 找出非零元素
                b->data[k].i = i;           // 记录非零元素行下标
                b->data[k].j = j;           // 记录非零元素列下标
                b->data[k].v = a[i][j];     // 保存非零元素
                k++;                        // 统计非零元素个数
            }
        }
    }
    b->m = m;
    b->n = n; // 记录矩阵行列
    b->t = k;           // 保存非零个数
}

void CreateTriTableTest() {

}

void TransTSMatrix(TSMatrix a, TSMatrix *b) {
    int p, q, col;
    b->m = a.n;
    b->n = a.m;   // M和T行列数互换
    b->t = a.t;               // 赋值非零元素个数
    if (b->t <= 0)
        printf("M中无非零元素！");
    else {
        q = 0;
        for (col = 0; col < a.n; col++) { // 列
            for (p = 0; p < a.t; p++) {   // 遍历整个三元组
                if (a.data[p].j == col) { // 如果三元组的列值和要取的列值相同
                    b->data[q].i = a.data[p].j;
                    b->data[q].j = a.data[p].i;
                    b->data[q].v = a.data[p].v;
                    q++;
                }
            }
        }
    }
}

void FastTransTSMatrix(TSMatrix a, TSMatrix *b) {
    // 数组num，num[j]存放矩阵在j列上非零元素的个数。用两个循环检查每个转置元素完成。
    // 数组rownext，rownext[i]代表转置矩阵第i行的下一个非零元素在b中的位置。
    int col, p, t, q;
    int *num, *rownext;
    num = (int *) calloc(a.n + 1, sizeof(DataType));
    rownext = (int *) calloc(a.m + 1, sizeof(DataType));
    // 初始化b
    b->m = a.n;
    b->n = a.m;
    b->t = a.t;
    if (b->t) {
        // 初始化num数组
        for (col = 0; col < a.n; col++) num[col] = 0;
        for (t = 0; t < a.t; t++) num[a.data[t].j]++; // 计算每列非零元素的个数
        rownext[0] = 0;
        for (col = 1; col < a.n; col++)
            rownext[col] = rownext[col - 1] + num[col - 1];
        for (p = 0; p < a.t; p++) { // 遍历完所有值（i行是有序的）
            col = a.data[p].j;      // 列的值
            q = rownext[col];       // col列的下标是从q开始的
            b->data[q].i = a.data[p].j; // 赋值
            b->data[q].j = a.data[p].i;
            b->data[q].v = a.data[p].v;
            rownext[col]++;         // 每赋一个三元组的值，要把所在列的位置向后移动
        }
    }
}
