#include <iostream>
#include <iomanip>
using namespace std;

int main() {

    int n = 65535, m = 20;
    // 分别输出证书的十进制、十六进制和八进制
    cout << n << "=" << hex << n << "=" << oct << n << endl;
    // 使用setbase分别输出整数的十进制、十六进制和八进制
    cout             << setbase(10) << m << "=" << setbase(16) << m << "=" << setbase(8) << m << endl;
    // 使用showbase显示数值进制的前缀
    cout << showbase << setbase(10) << m << "=" << setbase(16) << m << "=" << setbase(8) << m << endl;

    return 0;
}
