#include <iostream>
using namespace std;

int add(int x = 0, int y = 0);
void func(int a = 11, int b = 22, int c = 33);

int main() {
    
    // 定义带默认值的函数
    int z = add();
    cout << "z=" << z << endl;
    z = add(100, 200);
    cout << "z=" << z << endl;

    func(); // 没有参数
    func(1); // 1个参数
    func(1, 2); // 2个参数
    func(1, 2, 3); // 3个参数

    return 0;
}

int add(int x, int y) { 
    return x + y; 
}

void func(int a, int b, int c) {
    cout << "a=" << a << ",b=" << b << ",c=" << c << endl;
}