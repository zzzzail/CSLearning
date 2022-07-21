#include <iostream>

using namespace std;

int &refValue(int &x);

/**
 * @brief 引用作为函数返回值
 *
 * @return int
 */
int main()
{
    int oneX = 10;
    int oneY = 20;
    refValue(oneX) = 30;             // 返回值是引用，可以作为左值使用
    cout << "oneX=" << oneX << endl; // 输出oneX=30
    refValue(oneY) = 40;             // 等价与oneY=40;
    cout << "oneY=" << oneY << endl;

    double d = 3.14;
    cout << d << endl;

    return 0;
}

int &refValue(int &x) { return x; }
