#include <stdio.h>
#include "BinTree.h"
#include "SeqStack.h"
#include "CirQueue.h"

void Preorder(BinTree BT) {
    if (BT != NULL) {
        printf("%c", BT->data); // 访问根结点
        Preorder(BT->lchild);   // 递归遍历左子树
        Preorder(BT->rchild);   // 递归遍历右子树
    }
}

void Preorder1(BinTree BT) {
    SeqStack S;
    InitStack(&S); // 初始化栈
    Push(&S, BT);  // 根结点指针压栈
    while (!StackEmpty(&S)) {
        BT = Pop(&S); // 出栈
        if (BT != NULL) {
            printf("%c", BT->data); // 访问结点，假设数据域为字符型
            Push(&S, BT->rchild);   // 右子树入栈
            Push(&S, BT->rchild);   // 左子树入栈
        }
    }
}

void Inorder(BinTree BT) {
    if (BT != NULL) {
        Inorder(BT->lchild);    // 递归遍历左子树
        printf("%c", BT->data); // 访问根结点
        Inorder(BT->rchild);    // 递归遍历右子树
    }
}

void Inorder1(BinTree BT) {
    SeqStack S;
    BinTreeNode *p;
    InitStack(&S); // 初始化栈
    Push(&S, BT);  // 将树压栈
    while (!StackEmpty(&S)) {
        while (GetTop(&S)) {
            Push(&S, GetTop(&S)->lchild); // 直到左子树为空为止
        }
        p = Pop(&S); // 空指针退栈
        if (!StackEmpty(&S)) {
            printf("%c", GetTop(&S)->data); // 访问根节点
            p = Pop(&S);
            Push(&S, p->rchild);          // 右子树压栈
        }
    }
}

void Inorder2(BinTree BT) {
    BinTreeNode *ST[100]; // 使用指针数组模拟栈
    int top = 0;          // 初始化数组
    ST[top] = BT;
    do {
        while (ST[top] != NULL) {   // 根结点及所有的左结点地址装入数组
            top = top + 1;
            ST[top] = ST[top - 1]->lchild;
        }
        top = top - 1;
        if (top >= 0) {                  // 判断数组中地址是否访问完
            printf("%c", ST[top]->data); // 访问结点
            ST[top] = ST[top]->rchild;   // 扫描右子树
        }
    }
    while (top != -1);
}

void Postorder(BinTree BT) {
    if (BT != NULL) {
        Postorder(BT->lchild);  // 递归遍历左子树
        Postorder(BT->rchild);  // 递归遍历右子树
        printf("%c", BT->data); // 访问根节点
    }
}

// 按层遍历二叉树，从上到下，从左至右
void TransLevel(BinTree BT) {
    CirQueue Q;
    InitQueue(&Q);
    if (BT == NULL) return; // 二叉树不能为空
    else {
        printf("%c", BT->data); // 输出根结点
        if (BT->lchild != NULL) {
            printf("%c", BT->lchild->data);  // 输出左子树根节点
            EnQueue(&Q, BT->lchild);         // 左子树入队列
        }
        if (BT->rchild != NULL) {
            printf("%c", BT->rchild->data);  // 输出右子树根节点
            EnQueue(&Q, BT->rchild);         // 右子树入队列
        }
    }
}

int BinTreeDepth(BinTree BT) {
    int depl, depr;
    if (BT == NULL) return 0; // 对于空树，返回值0，结束递归
    else {
        depl = BinTreeDepth(BT->lchild); // 计算左子树的深度
        depr = BinTreeDepth(BT->rchild); // 计算右子树的深度
        if (depl > depr)
            return depl + 1;
        else
            return depr + 1;
    }
}

int found = 0; // 标记是否找到
BinTreeNode *p;

DataType FindBT(BinTree BT, DataType x) {
    // 如果BT不为空，并且没有找到结点，则继续往下查找。
    // 否则不继续递归往下查找。
    // 如果BT为空，或找到了结点，则不继续往下找。
    if (BT != NULL && !found) {
        if (BT->data == x) { // 先判断根节点是否与x匹配
            p = BT;
            found = 1;
        }
        else {
            FindBT(BT->lchild, x); // 遍历左子节点
            FindBT(BT->rchild, x); // 遍历右子节点
        }
    }
}

int Level(BinTree BT, BinTreeNode *p, int lh) {
    static int h = 0; // 标记高度
    if (BT == NULL)
        h = 0;
    else if (BT == p)
        h = 1;
    else {
        Level(BT->lchild, p, lh);
        if (h == 0) {                  // 表示左子树已经查找完
            Level(BT->rchild, p, lh);
        }
    }
}
