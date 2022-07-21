#include <stdio.h>
#include "SeqStack.h"

// 圆括号匹配问题
int Expr() {
    SeqStack s;
    DataType ch, x;
    InitStack(&s);
    while ((ch = getchar()) != '\n') {
        if (ch == '(') { // 遇到左括号进栈
            Push(&s, ch);
        } else if (ch == ')') { // 遇到右括号时出栈
            if (StackEmpty(&s))
                return 0;
            else
                x = Pop(&s);
        }
    }
    if (StackEmpty(&s))
        return 1;
    else
        return 0;
}

// 判断否是回文字符串
int symmetry(char str[]);

// 数值转换，将十进制数字N转换成d进制数字
void conversion(int N, int d);