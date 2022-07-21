#include <stdio.h>
#include "HuffmanTree.h"

void select(HuffmanTree HT, int k, int *s1, int *s2) {
    int i, j;
    int min1 = 101;
    for (i = 1; i <= k; i++) {
        if (HT[i].weight < min1 && HT[i].parent == 0) {
            j = i;
            min1 = HT[i].weight;
        }
        *s1 = j;
        min1 = 32767;
        for (i = 1; i <= k; i++) {
            if (HT[i].weight < min1 && HT[i].parent == 0 && i != *s1) {
                j = i;
                min1 = HT[i].weight;
            }
        }
        *s2 = j;
    }
}

void CHuffmanTree(HuffmanTree HT) {
    int i, s1, s2;
    for (i = 1; i <= m; i++) { // 初始化HT
        HT[i].lchild = 0;
        HT[i].rchild = 0;
        HT[i].parent = 0;
        HT[i].weight = 0;
    }
    for (i = 1; i <= n; i++) {
        scanf("%f", &HT[i].weight);
        for (i = n + 1; i <= m; i++) { // 在HT[1..i-1]中选择parent为0且权值最小的两个根结点，其序号分别为s1和s2
            select(HT, i - 1, &s1, &s2);
        }
        HT[s1].parent = i;
        HT[s2].parent = i;
        HT[i].lchild = s1;
        HT[i].rchild = s2;
        HT[i].weight = HT[s1].weight + HT[s2].weight; // 权值之和
    }
}
