#include <iostream>
using namespace std;

int main() {

    int a1 = 3;
    const int a2 = a1; // 数据是常量
    int * a3 = &a1; // 普通指针指向普通变量，*a3 = 6 是正确的
    const int * a4 = &a1; // 数据是常量的，指针是变量
    int * const a5 = &a1; // 数据是变量，指针是常量
    int const * a6 = &a1; // 数据是常量，指针是变量
    int const * const a7 = &a1; // 数据是常量，指针也是常量
    const int * const a8 = &a1; // 数据时常量，指针也是常量

    return 0;
}