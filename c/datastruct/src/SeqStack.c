#include <stdio.h>
#include <stdlib.h>
#include "SeqStack.h"

// 初始化栈
void InitStack(SeqStack *s) {
    // 置空顺序栈，由于c数据的下标是从0开始的，所以栈中元素也是从0开始。
    // 栈顶为空时指针不能指向0，而能指向-1。
    s->top = -1;
}

// 判断栈是否为空
int StackEmpty(SeqStack *s) { return s->top == -1; }

// 判断栈是否满
int StackFull(SeqStack *s) { return StackSize == s->top; }

// 进栈
void Push(SeqStack *s, DataType d) {
    if (StackFull(s)) {
        printf("stack overflow");
        exit(0);
    } else {
        s->top++;
        s->data[s->top] = d;
    }
}

// 出栈
DataType Pop(SeqStack *s) {
    if (StackEmpty(s)) {
        printf("stack underflow");
        exit(0);
    } else {
        return s->data[s->top--];
    }
}

// 取栈顶元素（不删除栈顶元素）
DataType GetTop(SeqStack *s) {
    if (StackEmpty(s)) {
        printf("stack underflow");
        exit(0);
    } else {
        return s->data[s->top];
    }
}