typedef char DataType;

// 二叉树结点
typedef struct btnode {
    DataType data;       // 数据
    struct btnode *parent; // 父节点
    struct btnode *lchild; // 左子节点
    struct btnode *rchild; // 右子节点
} BinTreeNode;

// 二叉树
typedef BinTreeNode *BinTree;

// 前序打印二叉树
void Preorder(BinTree BT);

// 使用非递归调用的方式遍历二叉树
void Preorder1(BinTree BT);

// 中序打印二叉树
void Inorder(BinTree BT);

// 使用非递归调用方式遍历二叉树
void Inorder1(BinTree BT);

// 使用非递归调用方式遍历二叉树
void Inorder2(BinTree BT);

// 后续打印二叉树
void Postorder(BinTree BT);

// 非递归调用的按层遍历二叉树表树
void TransLevel(BinTree BT);

// 求二叉树的深度
int BinTreeDepth(BinTree BT);

// 查找值为x的结点
DataType FindBT(BinTree BT, DataType x);

// 求结点x在树种的层次
int Level(BinTree BT, BinTreeNode *p, int lh);

