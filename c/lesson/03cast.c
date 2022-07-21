#include <stdio.h>

int main(int argc, char const *argv[])
{
    
    // short类型占用两个字节
    short x = -4321;
    unsigned short y = (unsigned short) x;

    // int 占用4个字节
    int a = 125667, b = -12866;
    // short占用2个字节
    short c = (short) a, d = (short) b;

    short x = -4321;
    int m = x;
    unsigned short n = (unsigned short) x;
    unsigned int p = n;

    return 0;
}
