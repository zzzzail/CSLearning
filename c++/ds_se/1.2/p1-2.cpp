#include <iostream>
using namespace std;

/**
 * @brief 强制类型转换运算符
 * 
 * static_cast用于将一种数据类型转换成另一种数据类型
 * static_cast<类型名>(表达式)
 * 
 * const_cast方法的使用。
 * 
 * @return int 
 */
int main() {

    int oneInt = 10;
    // 类型转换
    short oneShort = static_cast<short>(oneInt);
    cout << "oneShort = " << oneShort << endl;
    short oneShort2 = short(oneInt); // 强制类型转换新形式
    short oneShort3 = (short) oneInt; // 强制类型转换旧有形式
    short oneShort4 = oneInt; // 自动类型转换（注意会丢失精度的问题）

    // const_cast 用于去除指针和引用的常量性
    int a = 10;
    const int *p = &a; // 不能使用常量指针p修改a的值
    const int ca = 30; // 被const修饰
    int *q;
    cout << "a的地址为：\t" << &a << "\ta的值为：\t" << a << endl;
    cout << "*p指向的地址为：\t" << p << "\t*p的值为：\t" << *p << endl;

    q = const_cast<int *>(p); // 去除p的常量性赋值给q，如果写q=p的；会报错
    *q = 20; // 如果写*p = 20; 是错误的
    cout << "a的地址为：\t" << &a << "\ta的值为：\t" << a << endl;
    cout << "*p指向的地址为：\t" << p << "\t*p的值为：\t" << *p << endl;
    cout << "*q指向的地址为：\t" << q << "\t*q的值为：\t" << *q << endl;
    cout << "------ 分界线 ------" << endl;

    p = &ca; // ca的值不能修改
    q = const_cast<int *>(p); // 取出p的常量属性赋值给q，如果写成q=p；会报错
    *q = 40; // *p = 40; 错误，因为不能给常量指针的值赋值
    cout << "ca的地址为：\t" << &ca << "\tca的值为：\t" << ca << endl;
    cout << "*p指向的地址为：\t" << p << "\t*p的值为：\t" << *p << endl;
    cout << "*q指向的地址为：\t" << q << "\t*q的值为：\t" << *q << endl;
    cout << "------ 分界线 ------" << endl;

    const int num1 = 20;
    int num2 = 40;
    const int *nump = &num1; // 被const修饰的指针
    nump = &num2; // 即使被const修饰的指针也可以修改指针的指向；但不可以修改指针指向的值
    cout << "*nump的值为：" << *nump << endl;
    num2 = 60;
    cout << "*nump的值为：" << *nump << endl;
    // *nump = 80; // 因为指针被const修饰，所以不可以修改指针指向的值
    // cout << "*nump的值为：" << *nump << endl;
    
    return 0;
}