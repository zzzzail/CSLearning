// 最大顶点数
#define MaxVertexNum 100

typedef char VertexType;

typedef int Adjmatrix;

// 邻接矩阵图
typedef struct {
    VertexType vexs[MaxVertexNum];                // 顶点数组，类型假定为char型
    Adjmatrix arcs[MaxVertexNum][MaxVertexNum];    // 邻接矩阵，假定为int型
} MGraph;

// 建立邻接矩阵
void CreateMGraph(MGraph *G, int n, int e);

/**
 * @brief 深度优先遍历
 * 深度优先搜索（也成为深度优先遍历）（Depth First Search，DFS）
 * 在图中，按照深度依次按照深度访问。
 * 1. 在图 G 中任选一顶点 v 为初始出发点，首先访问出发点 v，并将其标记为已访问。
 * 2. 从 v 出发搜索每个邻接点 w，若 w 未访问过，则以 w 为新的出发点出发，继续进行深度优先遍历，
 *    直到图中所有和 v 有路径想通的顶点都被访问过。
 * 3. 若此时图中仍有顶点未被访问，则另选一个未曾访问过的顶点作为起点，重复上述过程，直到图中所有
 *    顶点都被访问过。
 */
void DFS(MGraph *G);
