typedef int DataType;

// 链表节点
typedef struct node {
    DataType data;
    struct node *next;
} ListNode;
// 链表指针
typedef ListNode *LinkedList;

// 使用头插法建立链表
LinkedList CreateListF();

// 使用尾插法建立链表
LinkedList CreateListR();

// 带有头结点的尾插法建立链表
LinkedList CreateListR1();

// 按序号查找第i个结点的值
ListNode *GetNodei(LinkedList head, int i);

// 按值查找
ListNode *LocateNodek(LinkedList head, DataType k);

// 向第i个结点插入值x
void InsertList(LinkedList head, int i, DataType x);

// 删除第i个节点，并返回删除的值
DataType DeleteList(LinkedList head, int i);

/**
 * 分解链表a，将值为奇数的放在a链表里，值为偶数的放在b链表里
 */
void split(LinkedList a, LinkedList b);

// 合并两个链表
LinkedList MergeList(LinkedList la, LinkedList lb);
