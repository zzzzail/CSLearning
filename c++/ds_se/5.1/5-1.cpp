#include <iostream>

class A;

class B : public A;

/**
 * @brief 基类与派生类的定义
 * 
 * 由基类派生出子类；
 * 子类继承于父类（基类）；
 * 
 * @return int 
 */
int main() {
    return 0;
}

class A {
private:
    int x;
    int y;

public:
    int getX() {
        return this->x;
    }

    void setX(int &x) {
        this->x = x;
    }

    int getY() {
        return this->y;
    }

    int setY(int &y) {
        this->y = y;
    }
};

class B : public A {
};