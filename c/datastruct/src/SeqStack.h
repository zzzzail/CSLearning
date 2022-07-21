#define StackSize 100

typedef char DataType;

// 顺序栈
typedef struct {
    DataType data[StackSize];
    int top;
} SeqStack;

// 初始顺序栈
void InitStack(SeqStack *S);

// 判断栈是否为空
int StackEmpty(SeqStack *S);

// 判断栈是否满
int StackFull(SeqStack *S);

// 进栈
void Push(SeqStack *s, DataType x);

// 出栈
DataType Pop(SeqStack *S);

// 区栈顶元素，不出栈，不删除栈顶元素
DataType GetTop(SeqStack *S);


