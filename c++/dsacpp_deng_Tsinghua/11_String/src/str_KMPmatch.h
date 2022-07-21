//
// Created by zail on 2022/1/19.
//
#include <string>

using namespace std;

#ifndef INC_11_STRING_STR_KMPMATCH_H
#define INC_11_STRING_STR_KMPMATCH_H

/**
 * 使用KMP算法实现字符串匹配
 *
 * @param str
 * @param substr 子串
 * @return
 */
int KMPmatch(string &str, string &substr);

void test_KMPmatch();

#endif //INC_11_STRING_STR_KMPMATCH_H
