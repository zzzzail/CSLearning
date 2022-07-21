#include <stdio.h>

/**
 * 
 * #include 是引入文件的意思
 * <stdio.h> 是引入 standard io 的 head 文件的意思。
 * 使用 <> 引入的文件是 c 自带的源文件，也可以使用 "" 引入自己写的头文件，例如 #include "my.h"。
 * 注意 #include 语句的结束没有分号，因为 #include 是宏指令。
 * 
 * \n 表示换行
 */
int main() {

    printf("Hello World!");

    return 0; // 返回 0 说明程序没有错误。
}

