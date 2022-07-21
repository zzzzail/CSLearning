#include <string.h>
#include "HuffmanCode.h"

void HaffmanEncode(HuffmanTree HT, HuffmanCode HC) {
    int c, p, i;     // c和p分别指示HT中孩子和双亲的位置
    char cd[n + 1];  // 临时存放编码串
    int start;       // 指示编码在cd中其实位置
    cd[n] = '\0';    // 最后一位放上串结束符
    for (i = 1; i <= n; i++) {
        start = n;  // 起始位置
        c = i;      // 从叶子结点HT[i]开始上溯
        while ((p = HT[c].parent) > 0) { // 直到上溯到HT[c]是树根为止
            // 若HT[c]是HT[p]的左孩子，则生成代码0，否则生成代码1
            cd[--start] = (HT[p].lchild == c) ? '0' : '1';
            c = p;
        }
        strcpy(HC[i].bits, &cd[start]);  // 将临时串cd中的编码拷贝到编码表中
        HC[i].len = n - start;           // 保存编码长度
    }
}
