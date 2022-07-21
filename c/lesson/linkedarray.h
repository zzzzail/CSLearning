#ifndef _LINKEDARRAY_H_
#define _LINKEDARRAY_H_

typedef struct {
    int *array;
    int size;
} Array;

// 创建数组
Array array_create(int init_size);
// 释放数组所占的空间
void array_free(Array *arr);
// 查看数组大小
int array_size(const Array *arr);
// 返回数组下标中的数据
int* array_at(Array *arr, int index);
// 对数组进行扩容
void array_inflate(Array *arr, int more_size);

#endif