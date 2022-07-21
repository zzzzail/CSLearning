#define ListSize 100

typedef int DataType;
// 顺序表
typedef struct {
    DataType data[ListSize];
    int length;
} SeqList;

// 插入，往i位置上插入值x
void InsertList(SeqList *L, int i, DataType x);

// 删除第i位置上的值
DataType DeleteList(SeqList *L, int i);

// 将线性表逆置
SeqList Converts(SeqList *L);

/**
 * 求最大值和最小值，以及最大值所在的位置和最小值所在的位置
 * @param L 顺序表
 * @param max 最大值指针
 * @param min 最小值指针
 * @param k 最大值所在的下标
 * @param j 最小值所在的下标
 */
void MaxAndMin(SeqList *L, DataType *max, DataType *min, int *k, int *j);

