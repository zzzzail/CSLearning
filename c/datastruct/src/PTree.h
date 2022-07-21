#define MaxTreeSize 100

// 数据结点类型
typedef char DataType;

// 树结点
typedef struct {
    DataType data; // 树结点数据域
    int parent;    // 双亲位置域
} PTNode;

// 树（双亲表示法）
typedef struct {
    PTNode nodes[MaxTreeSize];    // 结点数组
    int n;                        // 结点数量
} PTree;

