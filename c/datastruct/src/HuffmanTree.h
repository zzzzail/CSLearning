#define n 100        // 叶结点数
#define m 2*n-1      // 哈夫曼树中结点总数

// 哈夫曼树结点
typedef struct {
    float weight;                  // 权值
    int lchild, rchild, parent;    // 左右孩子及双亲指针
} HTNode;

// 哈夫曼树，0号单元不用
typedef HTNode HuffmanTree[m + 1];

// 在HT[1..k]中选择parent为0且权值最小的两个根结点，其序号分别存储到s1和s2指向的对应变量中
void select(HuffmanTree HT, int k, int *s1, int *s2);

// 构造哈夫曼树
void CHuffmanTree(HuffmanTree HT);
