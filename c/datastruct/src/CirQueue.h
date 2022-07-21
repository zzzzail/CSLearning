#define QueueSize 100

typedef char DataType;

// 循环队列
typedef struct {
    DataType data[QueueSize];
    int front, rear;
} CirQueue;

// 初始化队列
void InitQueue(CirQueue *Q);

// 判断队空
int QueueEmpty(CirQueue *Q);

// 判断队满
int QueueFull(CirQueue *Q);

// 入队列
void EnQueue(CirQueue *Q, DataType x);

// 取队头元素
DataType GetFront(CirQueue *Q);

// 出队列
DataType DeQueue(CirQueue *Q);

