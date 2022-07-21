// 对称矩阵
#define SMatrixScala 3

typedef int DataType;

// 获取矩阵中i行j列的数据
DataType SMatrixGet(DataType sm[], int i, int j);

// 对称矩阵的乘法
void SMatrixMultiply(DataType a[], DataType b[], DataType r[SMatrixScala][SMatrixScala]);

// 对称矩阵乘法测试
void SMatrixMultiplyTest();
