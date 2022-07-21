#define QueueSize 100
typedef int DataType;

// 顺序循环队列
typedef struct {
    DataType data[QueueSize];
    int front;
    int rear;
} SeqQueue;

// 初始化队列
void InitQueue(SeqQueue *Q);

// 判断队列是否为空
int QueueEmpty(SeqQueue *Q);

// 入队
void EnQueue(SeqQueue *Q, DataType x);

// 出队
DataType DeQueue(SeqQueue *Q);

// 获取队头元素，但不出队
DataType GetFront(SeqQueue *Q);
