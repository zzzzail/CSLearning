typedef char DataType;

// 线索数结点类型
typedef struct node {
    DataType data;
    /**
     * 左右线索
     * ltag = 0：左结点指针指向当前结点的左结点
     * ltag = 1：做结点指针指向当前结点的父结点
     */
    int ltag, rtag;
    struct node *lchild, *rchild;
} BinThrTreeNode;

// 定义线索树
typedef BinThrTreeNode *BinThrTree;

// 中序线索
void InorderThread(BinThrTree bt);

// 查找p结点的后继节点
BinThrTreeNode *InorderNext(BinThrTreeNode *p);

// 遍历线索二叉树
void ThinorderThrTree(BinThrTree bt);
