package com.java.dsa.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * @link https://leetcode-cn.com/problems/valid-anagram/
 * 有效字母异位词
 * 给定两个单词，两个单词其中的字母出现次数是相同的，位置可以是不同的。
 */
public class P242ValidAnagram1 {
    
    public static void main(String[] args) {
        P242ValidAnagram1 solution = new P242ValidAnagram1();
        String s1 = "anagram";
        String s2 = "nagaram";
        boolean res1 = solution.isAnagram(s1, s2);
        System.out.println(res1);
    }
    
    /**
     * 1. 暴力求解
     * 遍历 s 中的字符，根据字符在 t 中找，若有相同的，则继续找，若没有则，快速结束。
     * 直到找完 s 中的所有字符为止。
     * 重复元素？
     * 用一个数组，记录 t 中的字符是否已使用，在查找的过程中不可以使用已使用的字符。
     * 时间复杂度：O(s * t)
     * 空间复杂度：O(t)
     * 2. 利用 HashMap 求解，现将 s 中的所有字符加到到 HashMap 中，key 为字符本身，val 为
     * 出现的次数。
     * 后将 t 中的字符也依次加入到，但是过程中是减去出现的次数
     * 若某个字符没有在 HashMap 中则直接返回 false
     * 若某个字符减去 val 后 < 0 则说明 t 中出现次数多了，并返回 false
     * 时间复杂度：O(s)
     * 空间复杂度：O(s)
     * 3. 长度相等 && 数值相同 就可以看作是异位词吗？
     * 不可以!
     */
    public boolean isAnagram(String s, String t) {
        if (s == null || t == null) return false;
        if (s.length() != t.length()) return false;
        
        Map<Byte, Integer> map = new HashMap<>();
        for (Byte b : s.getBytes()) {
            Integer val = map.get(b);
            map.put(b, val == null ? 1 : val + 1);
        }
        for (Byte b : t.getBytes()) {
            Integer val = map.get(b);
            if (val == null) return false;
            --val;
            if (val < 0) return false;
            map.put(b, val);
        }
        return true;
    }
}
