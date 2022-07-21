#include <iostream>
#include <string>

using namespace std;

int main() {

    int orderInt1, orderInt2;
    char strArray[20];
    string str;
    double oneDouble;
    char oneChar = 'a';
    cout << "输入两个整数值，一个字符，一个字符串和一个浮点值，";
    cout << "以空格、Tab键或回车Enter键分隔：" << endl;

    cin >> orderInt1 >> orderInt2 >> oneChar >> strArray >> oneDouble;
    str = strArray;

    cout << "输入的数据是：" << endl; // endl 的作用是换行
    cout << "字符串是：\t\t" << str << endl // \t是制表符Tab
         << "两个整型值分别是：\t" << orderInt1 << " 和\t" << orderInt2 << endl
         << "字符是：\t\t" << oneChar << "\n" // \n是换行符，和endl效果相同
         << "浮点值是：\t\t" << oneDouble << endl;

    cout << "字符串如果太长可以用\
    换行写，但是前后的空格会保留下来，\
    如果不想有空格，\
    则起新的一行开头不要有空格。" << endl;

    return 0;
}