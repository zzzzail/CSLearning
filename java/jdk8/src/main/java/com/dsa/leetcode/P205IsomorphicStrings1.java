package com.dsa.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * @link https://leetcode.cn/problems/isomorphic-strings/
 * 同构字符串
 */
public class P205IsomorphicStrings1 {
    
    public static void main(String[] args) {
    }
    
    public boolean isIsomorphic(String s, String t) {
        return isIsomorphic2(s, t) && isIsomorphic2(t, s);
    }
    
    public boolean isIsomorphic2(String s, String t) {
        if (s.length() != t.length()) return false;
        if (s.length()== 0 /* && t.length() == 0 */) return true;
    
        Map<Character, Character> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char sc = s.charAt(i);
            char tc = t.charAt(i);
            if (map.containsKey(sc)) {
                if (map.get(sc) != tc) return false;
            }
            else {
                map.put(sc, tc);
            }
        }
        return true;
    }
}
