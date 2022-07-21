#include <stdio.h>
#include <stdlib.h>
#include "GList.h"

/**
 * 通过用户输入的广义表表达式建立相应的广义表，边输入边建立。
 * 基本思想：在广义表中，遇到左括号"("递归构造子表，否则构造原子节点；
 * 遇到逗号递归构造后续广义表，直到表达式字符串输入结束（假设结束符为";"）
 */
GList CreateGList(GList GL) {
    char ch;
    while ((ch = getchar()) != '\n') {
        printf("-------%c", ch);
        if (ch != ' ') {
            GL = (GLNode *) malloc(sizeof(GLNode));
            if (ch == '(') {
                GL->tag = list;
                GL->slink = CreateGList(GL->slink); // 递归调用构造子表
            }
            else {
                GL->tag = atom;
                GL->data = ch;
            }
        }
        else GL = NULL;
        scanf("%c", &ch);
        if (GL != NULL) {
            if (ch == ',') {
                GL->link = CreateGList(GL->link); // 递归构造后续广义表
            }
            else {
                GL->link = NULL; // 表示遇到')'或结束符号';'时，无后续表
            }
        }
    }
    return GL;
}

void CreateGListTest() {
    GList GL;
    GL = CreateGList(GL);
}

// 打印广义表
void PrintGList(GList GL) {
    if (GL != NULL) {
        if (GL->tag == list) {
            printf("(");
            if (GL->slink == NULL) printf(" ");
            else PrintGList(GL->slink); // 递归调用输出子表
        }
        else
            printf("%c", GL->data);
        if (GL->tag == list)
            printf(")");
        if (GL->link != NULL) {
            printf(",");
            PrintGList(GL->link); // 递归调用输出下一个结点
        }
    }
}

// 查找广义表中的数据
void FindGListX(GList GL, DataType x, int *mark) {
    /**
     * 若遇到tag=0的原子结点，如果是要找的结点，则查找成功
     * 否则若还有元素，则递归调用本过程，查找后续元素，直到遇到link为NULL的元素
     * f(p, x) = true                                             (p->tag = 0 且 p->data = x)
     * f(p, x) = f(p->link, x)                                    (p->tag = 0 且 p->data != x)
     * f(p, x) = f(p->link, x) 或 f(p, x) = f(p->slink, x)        (p->tag = 1)
     */
    if (GL != NULL) {
        if (GL->tag == atom && GL->data == x) { // 如果查找到元素
            *mark = 1;
            return;
        }
        else { // 查找不到的话
            // 如果是子表，则先查找子表
            if (GL->tag == list)
                FindGListX(GL->slink, x, mark);
            // 如果有下一个结点，则继续查找下一个结点
            FindGListX(GL->link, x, mark);
        }
    }
}

// 求广义表的表头
GList head(GList GL) {
    // 一个广义表的表头指的是该广义表的第一个元素
    GList p;
    if (GL != NULL && GL->tag != atom) { // 不为空且不是原子节点
        p = GL->slink;
        p->link = NULL;
        return p;
    }
    else
        return NULL;
}

// 求广义表的表尾
GList tail(GList GL) {
    GList p;
    if (GL != NULL && GL->tag != atom) { // 表部位空且有表尾
        p = GL->slink;
        p = p->link; // p指向第二个元素
        GL->slink = p; // 删除广义表第一个元素
    }
    return p;
}

// 求广义表的深度
void depth(GList GL, int *maxdh) {
    /**
     * 扫描广义表的第一层的每一个结点，对每个节点递归调用计算出其表的深度，取最大的子表深度然后加1
     * 即为广义表的最大深度
     * 递归模型：
     * maxdh(GL) = 0                  (GL为单个元素即GL->tag = atom)
     * maxdh(GL) = 1                  (GL为空表即GL->tag = 1 且 GL->slink = NULL)
     * maxdh(GL) = max(maxdh(GL1), maxdh(GL2), ..., maxdh(GLn)) + 1
     */
    int h;
    if (GL->tag == atom) *maxdh = 0; // 广义表为单个元素
    else {
        if (GL->tag == list && GL->slink == NULL)
            *maxdh = 1; // 广义表为空表
        else {                                  // 递归求解
            GL = GL->slink;                     // 递归第一层
            *maxdh = 0;
            do {                                // 循环扫描表的第一层的每个结点，对每个结点求其子表的深度
                depth(GL, &h);
                if (h > *maxdh) *maxdh = h;     // 取最大的子表深度
                GL = GL->link;
            }
            while (GL != NULL);
            *maxdh = *maxdh + 1;                // 子表的最大深度+1
        }
    }
}