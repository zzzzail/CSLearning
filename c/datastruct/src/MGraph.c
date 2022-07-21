#include "MGraph.h"

void CreateMGraph(MGraph *G, int n, int e) {
    // 采用邻接矩阵表示法构造无向网G，n、e表示图的当前顶点数和边数
    int i, j, k, w;
    scanf("%d,%d", &n, &e);      // 读入顶点数和边数
    for (i = 0; i < n; i++) {    // 输入顶点信息
        scanf("%c", &G->vexs[i]);
    }
    for (i = 0; i < n; i++) {
        for (j = 0; j < n; j++) {
            G->arcs[i][j] = INT_MAX; // 初始化邻接矩阵元素为无穷大
        }
    }
    for (k = 0; k < e; k++) {            // 读入e条边，建立邻接矩阵
        scanf("%d,%d,%d", &i, &j, &w);   // 读入一条边的两端顶点序号i、j及边上的权w
        G->arcs[i][j] = w;               // 设置矩阵对称元素权值
        G->arcs[j][i] = w;
    }
}

void DFS(MGraph *G) {
    int i = 0;
    int n = 20;
    __DFS_Recursion(G, i, n);
}

int visited[20];
void __DFS_Recursion(MGraph * G, int i, int n) {
    int j;
    printf("访问 %d\n", i);  // 从顶点 i 出发
    visited[i] = 1;         // 标记 vi 已访问过
    for ( j = 0; j < n; j ++ )                  // 依次搜索 vi 的每个邻接点
        if ( G.arcs[i][j] == 1 && !visited[j] ) // 若没有邻接点没有被访问
            __DFS_Recursion(G, j, n);           // 递归调用邻接点
}
