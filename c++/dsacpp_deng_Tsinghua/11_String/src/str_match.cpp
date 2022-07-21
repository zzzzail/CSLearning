//
// Created by zail on 2022/1/18.
//
#include <iostream>
#include <string>

using namespace std;

/**
 * 匹配子字符串的蛮力算法
 *
 * ---------------------------------------------------------------------------------------------------
 * str    = 000001
 * substr = 001
 *
 * 第一次匹配
 *                 ×
 *               000001
 *               001
 *               i = 2; j = 2;
 * 第二次匹配
 *                  ×
 *               000001
 *                001
 *               i = 3; j = 2;
 * 第三次匹配
 *                   ×
 *               000001
 *                 001
 *               i = 4; j = 2;
 * 第四次匹配
 *               000001
 *                  001
 *               i = 6; j = 3;
 *               返回
 * ---------------------------------------------------------------------------------------------------
 *
 * @return 返回匹配到子串的起始位置，若没有匹配则返回-1
 */
int match(const string &str, const string &substr) {
    int i = 0, j = 0;
    while (j < substr.length() && i < str.length()) {
        if (str[i] == substr[j]) { // 单字符匹配，则继续匹配
            i++;
            j++;
        }
        else {
            i = i - j + 1; // 进入下一个匹配
            j = 0;
        }
        // 优化：这里不需要每次都判断是否可返回
        // if (j == substr.length()) return i - substr.length(); // 匹配到返回匹配的位置
    }
    // return -1; // 匹配不到返回-1
    
    // 如果匹配到了，则说明 i - j 就是子串所在的起始位置
    // 若没有匹配到，说明没有匹配完所有的子串就退出了 j < substr.length()
    return j < substr.length() ? -1 : i - j;
}

int test_match() {
    string str1 = "000001";
    string substr1 = "001";
    int p1 = match(str1, substr1);
    cout << "\"" << substr1 << "\"" << "在" << "\"" << str1 << "\"" << "中的第" << p1 << "位置。" << endl;
    assert(p1 == 3);
    
    string str2 = "000000";
    string substr2 = "001";
    int p2 = match(str2, substr2);
    cout << "\"" << substr2 << "\"" << "在" << "\"" << str2 << "\"" << "中的第" << p2 << "位置。" << endl;
    assert(p2 == -1);
    
    string str3 = "000000";
    string substr3 = "2";
    int p3 = match(str3, substr3);
    cout << "\"" << substr3 << "\"" << "在" << "\"" << str3 << "\"" << "中的第" << p3 << "位置。" << endl;
    assert(p3 == -1);
    
    string str4 = "000000";
    string substr4 = "00000";
    int p4 = match(str4, substr4);
    cout << "\"" << substr4 << "\"" << "在" << "\"" << str4 << "\"" << "中的第" << p4 << "位置。" << endl;
    assert(p4 == 0);
    
    string str5 = "010110";
    string substr5 = "001";
    int p5 = match(str5, substr5);
    cout << "\"" << substr5 << "\"" << "在" << "\"" << str5 << "\"" << "中的第" << p5 << "位置。" << endl;
    assert(p5 == -1);
}

/******************************************************************************************
 * Text     :  0   1   2   .   .   .   i-j .   .   .   .   i   .   .   n-1
 *             ------------------------|-------------------|------------
 * Pattern  :                          0   .   .   .   .   j   .   .
 *                                     |-------------------|
 ******************************************************************************************/
int match(char *P, char *T) { // 串匹配算法（Brute-force-1）
    size_t n = strlen(T), i = 0; // 文本串长度、当前接受比对字符的位置
    size_t m = strlen(P), j = 0; // 模式串长度、当前接受比对字符的位置
    while (j < m && i < n) { // 自左向右逐个比对字符
        if (T[i] == P[j]) { // 若匹配
            i++;
            j++;
        } // 则转到下一对字符
        else { // 否则
            i -= j - 1;
            j = 0;
        } // 文本串回退、模式串复位
    }
    return i - j; // 如何通过返回值，判断匹配结果？
}