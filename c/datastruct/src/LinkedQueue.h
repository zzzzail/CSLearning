typedef int DataType;

typedef struct qnode {
    DataType data;
    struct qnode *next;  // 下一个节点指针
} QueueNode;

// 链队列
typedef struct {
    QueueNode *front; // 头指针
    QueueNode *rear;  // 尾指针
} LinkedQueue;