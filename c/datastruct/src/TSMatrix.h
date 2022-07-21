#define MaxSize 1000

typedef int DataType;

// 三元组结点
typedef struct {
    int i, j; // 非零元素的行号和列号（下标）
    DataType v; // 非零元素值
} TriTupleNode;

// 稀疏矩阵的类型
typedef struct {
    TriTupleNode data[MaxSize]; // 存储三元组的数组
    int m, n, t; // 矩阵的行数、列数和非零元素个数
} TSMatrix;

/**
 * 建立顺序存储稀疏矩阵的三元组表
 * 将矩阵a转换成三元组表，存放在b中
 */
void CreateTriTable(TSMatrix *b, int a[][5], int m, int n);

void CreateTriTableTest();

/**
 * 三元组表结构存储的稀疏矩阵的转置运算 (一般转置算法)
 * 将a的转置存放在b中
 */
void TransTSMatrix(TSMatrix a, TSMatrix *b);

void TransTSMatrixTest();

// 快速转置算法
void FastTransTSMatrix(TSMatrix a, TSMatrix *b);

void FastTransTSMatrixTest();
