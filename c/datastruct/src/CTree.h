#define MaxTreeSize 100

// 数据类型
typedef char DataType;

// 孩子链表节点类型
typedef struct cnode {
    int child;            // 孩子结点在指针数组中的序号
    struct cnode *next;
} CNode;

// 指针数组结点类型
typedef struct {
    DataType data; // 树中结点数据域
    CNode *firstchild; // 孩子结点头指针
} PANode;

typedef struct {
    PANode nodes[MaxTreeSize]; // 指针数组
    int n, r;                  // n为结点数，r为根节点在指针数组中的位置（即下标）
} CTree;


