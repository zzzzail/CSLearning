#include <iostream>
#include <string>
using namespace std;

int main() {

    string str = "assassass";
    cout << str.size() << endl;
    cout << str.length() << endl;

    return 0;
}

class A {
    public:
        operator double ();
};

A::operator double () {
    return 1;
}