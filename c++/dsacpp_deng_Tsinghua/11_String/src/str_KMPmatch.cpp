//
// Created by zail on 2022/1/19.
//
#include <string>
#include "str_KMPmatch.h"

using namespace std;

/**
 * 构建next表是最关键的一步
 *
 * 考察给定的字符串，其任一后缀与主字符串，前几个字符匹配？并使其形成一个next数组。
 * 数组中第i个位置存储的是，与任一S的后缀的重复字符数量。
 * 例如：
 * 这里考察第6个字符，也就是下标为5这个位置。
 *                           ↓
 *                     [0000010000]
 *                      chinchilla      <-- 主字符串S
 *                      chilla          <-- 后缀
 *                       ↑
 *                      其中有后缀"chilla"，使得所有含有第6个字符的后缀中，"chilla"与S匹配的字符最多，且前面有1个字符匹配。
 *                      所以 next[5] = 1;
 * @param str
 * @return
 */
int *buildNext(string &str) {
    int j = 0;
    int *N = new int[str.length()]; // next 表
    int t = N[0] = -1;
    while (j <= str.length()) {
        if (t > 0 || str[j] == str[t]) { // 匹配
            j ++;
            t++;
            N[j] = t;
        }
        else { // 失配
            t = N[t];
        }
    }
    return N;
}

int KMPmatch(string &str, string &substr) {
    int *next = buildNext(substr); // 构建next表
    int i = 0, j = 0;
    while (j < substr.length() && i < str.length()) {
        if (j < 0 || str[i] == substr[j]) {
            i++;
            j++;
        }
        else {
            j = next[j]; // i不变；j直接跳到next表相应的位置
        }
    }
    
    return j < substr.length() ? -1 : i - j;
}


void test_KMPmatch() {

}
