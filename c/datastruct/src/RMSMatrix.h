#define MaxSize 1000

// 三元组结点
typedef struct {
    int i, j; // 非零元素的行号和列号（下标）
    DataType v; // 非零元素值
} TriTupleNode;

typedef struct {
    TriTupleNode data[MaxSize];
    int RowPos[MaxSize]; // 没行第一个非零元素在三元组表中的位置
    int m, n, t;
};