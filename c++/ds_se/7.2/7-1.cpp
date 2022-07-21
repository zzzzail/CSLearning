#include <iostream>
using namespace std;

int main() {

    int x = 10, y = 3;
    // cin >> x >> y;
    // 把cin流重定向到文件test.txt
    freopen("test.txt", "w", stdout);
    if (y == 0) {
        cerr << "error." << endl;
    }
    else {
        cout << x << "/" << y << "=" << x/y << endl;
    }

    return 0;
}