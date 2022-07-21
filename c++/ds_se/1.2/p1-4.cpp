#include <iostream>
using namespace std;

int main() {

    int a = 100;
    int &aname = a;
    int &bname = a;
    int &cname = bname;
    cout << "a的地址为：\t" << &a << "\ta的值为：\t" << a << endl;
    cout << "&aname的地址为：\t" << &aname << "\t&aname的值为：\t" << aname << endl;
    cout << "&bname的地址为：\t" << &bname << "\t&bname的值为：\t" << bname << endl;
    cout << "&cname的地址为：\t" << &cname << "\t&cname的值为：\t" << cname << endl;

    cname = 200;
    cout << "a的地址为：\t" << &a << "\ta的值为：\t" << a << endl;
    cout << "&aname的地址为：\t" << &aname << "\t&aname的值为：\t" << aname << endl;
    cout << "&bname的地址为：\t" << &bname << "\t&bname的值为：\t" << bname << endl;
    cout << "&cname的地址为：\t" << &cname << "\t&cname的值为：\t" << cname << endl;

    cout << "------ 分割线 ------" << endl;

    // const int x = 10;
    // 报错
    // 将 "int &" 类型的引用绑定到 "const int" 类型的初始值设定项时，限定符被丢弃C/C++(433)
    // int &ax = x;

    int y = 20;
    const int & ay = y;
    cout << "ay=" << ay << endl;
    cout << "------ 分割线 ------" << endl;

    // int z = 30;
    // const int &az = z;
    // int &az2 = z;
    // 常量不允许更改，下列两行都报错
    // az = 100;
    // az = az2;
    // cout << "------ 分割线 ------" << endl;

    int oneInt = 1;
    int & ref = oneInt; // ref是oneInt的引用，ref等价于oneInt
    const int & refc = oneInt; // 定义常引用
    ref = 2; // 修改ref也修改了oneInt
    cout << "oneInt=" << oneInt << endl;
    cout << "ref=" << ref << endl;
    cout << "refc=" << refc << endl;

    oneInt = 3; // 修改oneInt也修改了ref
    cout << "oneInt=" << oneInt << endl;
    cout << "ref=" << ref << endl;
    cout << "refc=" << refc << endl;

    int & ref2 = ref;
    ref2 = 4; // 修改ref2也修改了ref
    cout << "oneInt=" << oneInt << endl;
    cout << "ref=" << ref << endl;
    cout << "refc=" << refc << endl;

    // refc = 5 // 不允许修改refc的值。不能使用常引用对所引用的变量进行修改

    return 0;
}