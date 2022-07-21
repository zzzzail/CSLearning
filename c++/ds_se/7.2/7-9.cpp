#include <iostream>
using namespace std;

int main() {

    double vals[] = {1.1, 1.23, 1.456, 1.7890, 1.12345};
    cout.fill('*');
    for ( int i = 0; i < sizeof(vals) / sizeof(double); i++ ) {
        cout << "vals[" << i << "]=(";
        cout.width(10);
        cout << vals[i] << ")" << endl;
    }

    cout.fill(' ');
    for ( int i = 0; i < sizeof(vals) / sizeof(double); i++ ) {
        cout << "vals[" << i << "]=(";
        cout.width(10);
        cout << vals[i] << ")" << endl;
    }

    return 0;
}