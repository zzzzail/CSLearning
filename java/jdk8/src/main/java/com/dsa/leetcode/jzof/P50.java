package com.dsa.leetcode.jzof;

import java.util.HashMap;
import java.util.Map;

/**
 * 第一个只出现依次的字符
 *
 * @author zail
 * @link https://leetcode.cn/problems/di-yi-ge-zhi-chu-xian-yi-ci-de-zi-fu-lcof/
 * @date 2022/7/17
 */
public class P50 {
    
    public static void main(String[] args) {
    
    }
    
    /**
     * 在字符串 s 中找出第一个只出现一次的字符，如果没有，返回一个空格。（s 只包含小写字母）
     */
    public char firstUniqChar(String s) {
        if (s == null || s.length() == 0) return ' ';
        
        Map<Character, Boolean> dic = new HashMap<>();
        char[] sc = s.toCharArray();
        for(char c : sc) dic.put(c, !dic.containsKey(c));
        for(char c : sc) if(dic.get(c)) return c;
        return ' ';
    }
}
