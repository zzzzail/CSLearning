typedef int DataType;

typedef struct dlnode {
    DataType data;
    struct dlnode *prior, *next;
} DLNode;

typedef DLNode *DLinkedList;

// 初始化链表
void DLInit(DLinkedList L);

// 向链表中插入值
void DLInsert(DLinkedList head, DataType x);

// 删除链表中的结点
DataType DLDelete(DLNode *p);

