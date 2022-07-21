package com.dsa.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @link https://leetcode.cn/problems/reverse-words-in-a-string/
 * 颠倒字符串中的单词
 */
public class P151ReverseWordsInAString1 {
    
    public static void main(String[] args) {
        P151ReverseWordsInAString1 solution = new P151ReverseWordsInAString1();
        String s1 = "the  sky is blue";
        String res1 = solution.reverseWords(s1);
        System.out.println(res1);
    }
    
    /**
     * 使用 split 的方式
     */
    public String reverseWords(String s) {
        s = s.trim();
        List<String> words = new ArrayList<>(Arrays.asList(s.split(" ")));
        words.removeIf(word -> word.length() == 0);
        if (words.size() == 0) return "";
        
        for (int i = 0; i < words.size() / 2; ++i) {
            String w1 = words.get(i);
            String w2 = words.get(words.size() - i - 1);
            words.set(i, w2);
            words.set(words.size() - i - 1, w1);
        }
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < words.size(); ++i) {
            res.append(words.get(i));
            if (i < words.size() - 1) res.append(" ");
        }
        return res.toString();
    }
    
    /**
     * 1ms 打败 100%
     */
    public String reverseWords2(String s) {
        // 源字符数组
        char[] initialArr = s.toCharArray();
        // 新字符数组
        char[] newArr = new char[initialArr.length + 1]; // 下面循环添加"单词 "，最终末尾的空格不会返回
        int newArrPos = 0;
        // i 来进行整体对源字符数组从后往前遍历
        int i = initialArr.length - 1;
        while (i >= 0) {
            while (i >= 0 && initialArr[i] == ' ') { // 跳过空格
                i--;
            }
            // 此时i位置是边界或 != 空格，先记录当前索引，之后的while用来确定单词的首字母的位置
            int right = i;
            while (i >= 0 && initialArr[i] != ' ') {
                i--;
            }
            // 指定区间单词取出(由于i为首字母的前一位，所以这里+1,)，取出的每组末尾都带有一个空格
            for (int j = i + 1; j <= right; j++) {
                newArr[newArrPos++] = initialArr[j];
                if (j == right) {
                    newArr[newArrPos++] = ' '; // 空格
                }
            }
        }
        // 若是原始字符串没有单词，直接返回空字符串；若是有单词，返回0-末尾空格索引前范围的字符数组(转成String返回)
        if (newArrPos == 0) {
            return "";
        }
        else {
            return new String(newArr, 0, newArrPos - 1);
        }
    }
    
    /**
     * 3 行代码解决
     */
    public String reverseWords3(String s) {
        String[] words = s.trim().split(" +");
        Collections.reverse(Arrays.asList(words));
        return String.join(" ", words);
    }
    
}
