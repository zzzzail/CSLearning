#include <stdio.h>

// 画出 n 个 * 前面有 whitespace 个空格
void printStar(int whitespace, int n) {
    for (int i = 0; i < whitespace; i++) printf(" ");
    for (int i = 0; i < n; i++) printf("*");
    printf("\n");
}

/**
 * 冰淇凌有基层
 * 第一层的宽度
 * 每一层的高度
 * 
 * 冰淇凌的头是有固定的形状组成
 * 
 * 3, 5, 2
 */
void drawIcecream(int layer, int width, int height) {
    // 首先计算出甜筒部分有多少个 *
    int count = width;
    for (int i = 1; i < layer; i ++) count += 4;
    // printf("底座有 %d 个 *\n", count);

    // 冰淇凌的第一层
    for (int i = 1; i < width; i += 2) printStar( (count / 2) - (i / 2), i);
    // 中间
    int _w = width;
    for (int i = 1; i < layer; i++) { // 第 i 层
        for (int h = 0; h < height; h++) printStar( (count / 2) - (_w / 2) , _w);
        _w += 4;
    }
    // 底座
    printStar(0, count);
}

int main() {
    drawIcecream(3, 5, 2);
    return 0;
}
