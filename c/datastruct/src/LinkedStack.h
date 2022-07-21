typedef int DataType;

// 链栈结点
typedef struct lsnode {
    DataType data;
    struct lsnode *next;
} StackNode;

// 链栈
typedef StackNode *LinkedStack;

// 初始顺序栈
void InitStack(LinkedStack *S);

// 判断栈是否为空
int StackEmpty(LinkedStack *S);

// 判断栈是否满
int StackFull(LinkedStack *S);

// 进栈
void Push(LinkedStack *s, DataType x);

// 出栈
DataType Pop(LinkedStack *S);

// 区栈顶元素，不出栈，不删除栈顶元素
DataType GetTop(LinkedStack *S);

// 括号匹配问题
int Expr(char str[], char l, char r);

// 判断否是回文字符串
int symmetry(char str[]);

// 数值转换，将十进制数字N转换成d进制数字
void conversion(int N, int d);
