#include <iostream>
#include <string>
using namespace std;

class Demo
{
public:
    Demo(int i, string pos) { // 构造函数
        id = i;
        position = pos;
        cout << "生成对象\t" << this << ",\t id = " << id << "\t位置：" << position << endl;
    }
    Demo(int i) : position("temp") { // 类型转换构造函数
        id = i;
        cout << "生成对象\t" << this << ",\t id = " << id << "\t位置：" << position << endl;
    }
    ~Demo() { // 析构函数
        cout << "消亡对象\t" << this << ",\t id = " << id << "\t位置：" << position << endl;
    }
private:
    int id;
    string position;
};

// 类外定义，全局作用域
Demo d1 = Demo(1, "全局");

/**
 * @brief 方法
 * 
 */
void Fun() {
    cout << "------ enter function ------" << endl;
    static Demo d2 = Demo(2, "函数Fun内，静态的"); // 函数内定义，静态局部的
    Demo d3 = Demo(3, "函数Fun内"); // 函数内定义，局部的
    cout << "exit func" << endl;
}

/**
 * @brief 类对象的生存期和作用域
 * 类的对象在生成时调用构造函数，在消亡时调用析构函数，在这两个函数调用之间即是对象的生存期。
 * 
 * @return int 
 */
int main() {
    cout << "------ enter main ------" << endl;
    /**
     * @brief 主函数内定义
     * 主函数体内定义的局部变量，会在主函数执行完成后释放占用的内存（执行析构函数）。
     */
    Demo d4 = Demo(4, "主函数内"); 
    d4 = 7;
    cout << "------ enter block ------" << endl;
    {
        Demo d5 = Demo(5, "主函数块内"); // 块内定义
    }
    cout << "------ exit block ------" << endl;
    static Demo d6 = Demo(6, "主函数内，静态的");
    Fun();
    cout << "main ends" << endl;

    return 0;
}

