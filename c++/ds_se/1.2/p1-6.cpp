#include <iostream>
using namespace std;

void SwapValue(int a, int b);

void SwapRef(int & a, int & b);

int main() {
    // 不同参数传递方式
    int a = 10, b = 20;
    cout << "数据交换前：\t\ta=" << a << ",b=" << b << endl;
    
    SwapValue(a, b);
    cout << "调用SwapValue()后：\t\ta=" << a << ",b=" << b << endl;
    
    a = 10, b = 20;
    SwapRef(a, b);
    cout << "调用SwapRef()后：\t\ta=" << a << ",b=" << b << endl;

    return 0;
}

void SwapValue(int a, int b) {
    int temp;
    temp = a;
    a = b;
    b = temp;
    cout << "在SwapValue()函数中：\t\ta=" << a << ",b=" << b << endl;
}

void SwapRef(int & a, int & b) {
    int temp = a;
    a = b;
    b = temp;
    cout << "在SwapRef()函数中：\t\ta=" << a << ",b=" << b << endl;
}
