#include "HuffmanTree.h"

#define n 100        // 叶结点数

// 哈夫曼编码结点
typedef struct {
    char ch;
    char bits[n + 1];
    int len;
} HaffmanCodeNode;

// 哈夫曼编码
typedef HaffmanCodeNode HuffmanCode[n + 1];

// 根据哈夫曼编树HT求哈夫曼编码表HC
void HaffmanEncode(HuffmanTree HT, HuffmanCode HC);
