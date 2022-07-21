#include <stdio.h>
#include  "BinThrTree.h"

void InorderThread(BinThrTree bt) {
    // 定义千趋结点的指针pre（静态变量），保存刚访问过的结点
    static BinThrTree *pre = NULL;
    if (bt != NULL) { // 当二叉树为空时结束递归
        InorderThread(bt->lchild); // 左子树线索化
        if (bt->lchild == NULL) bt->ltag = 1;
        else bt->ltag = 0;
        if (bt->rchild == NULL) bt->rtag = 1;
        else bt->rtag = 0;
        if (pre) {
            if (pre->rtag == 1) pre->rchild = bt; // 给前趋结点加后继线索
            if (bt->ltag == 1) bt->lchild = pre; // 给当前结点加前趋线索
        }
        pre = bt;
        InorderThread(bt->rchild); // 右子树线索化
    }
}

BinThrTreeNode *InorderNext(BinThrTreeNode *p) {
    // 在中序线索二叉树上求结点*p的中序后继节点
    if (p->rtag == 1) { // rchild 域为右线索
        return p->rchild; // 返回中序后继节点指针
    }
    else {
        p = p->rchild; // 从*p的右孩子开始
        while (p->ltag == 0) {
            p = p->lchild; // 沿左指针链向下查找
        }
        return p;
    }
}

void ThinorderThrTree(BinThrTree bt) {
    BinThrTreeNode *p;
    if (bt != NULL) {
        p = bt;
        while (p->ltag == 0) { // 使p指向根节点
            p = p->lchild;     // 查找中序遍历的第一个结点
        }
        do {
            printf("%c", p->data); // 输出访问结点
            p = InorderNext(p);    // 查找结点*p的中序后继
        }
        while (p != NULL);         // 当p为空时算法结束
    }
}
