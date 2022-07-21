package com.dsa.leetcode;

import java.util.*;

/**
 * @link https://leetcode.cn/problems/find-all-anagrams-in-a-string/
 * 找到字符串中所有字母异位词
 */
public class P438FindAllAnagramsInAString1 {
    
    public static void main(String[] args) {
        P438FindAllAnagramsInAString1 solution = new P438FindAllAnagramsInAString1();
        List<Integer> res1 = solution.findAnagrams("abab", "ab");
        System.out.println(res1);
    
        List<Integer> res2 = solution.findAnagrams3("cbaebabacd", "abc");
        System.out.println(res2);
    }
    
    /**
     * 暴力解法
     */
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> res = new ArrayList<>();
        char[] arr = s.toCharArray();
        int n = arr.length;
        for (int i = 0; i <= n - p.length(); ++i) {
            if (isAnagrams(arr, i, i + p.length() - 1, p)) {
                res.add(i);
            }
        }
        return res;
    }
    
    private boolean isAnagrams(char[] arr, int left, int right, String p) {
        char[] newArr = new char[right - left + 1];
        System.arraycopy(arr, left, newArr, 0, right - left + 1);
        Arrays.sort(newArr);
        char[] pArr = p.toCharArray();
        Arrays.sort(pArr);
        return Arrays.equals(newArr, pArr);
    }
    
    /**
     * 滑动窗口
     */
    public List<Integer> findAnagrams2(String s, String p) {
        // 用于返回字母异位词的起始索引
        List<Integer> res = new ArrayList<>();
        // 用 map 存储目标值中各个单词出现的次数
        Map<Character, Integer> map = new HashMap<>();
        for (Character c : p.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        // 用另外一个 map 存储滑动窗口中有效字符出现的次数
        Map<Character, Integer> window = new HashMap<>();
        int left = 0; // 左指针
        int right = 0; // 右指针
        int valid = p.length(); // 只有当 valid == 0 时，才说明 window 中包含了目标子串
        while (right < s.length()) {
            // 如果目标子串中包含了该字符，才存入 window 中
            if (map.containsKey(s.charAt(right))) {
                window.put(s.charAt(right), window.getOrDefault(s.charAt(right), 0) + 1);
                // 只有当 window 中该有效字符数量不大于map中该字符数量，才能算一次有效包含
                if (window.get(s.charAt(right)) <= map.get(s.charAt(right))) {
                    valid--;
                }
            }
            // 如果 window 符合要求，即两个 map 存储的有效字符相同，就可以移动左指针了
            // 但是只有二个 map 存储的数据完全相同，才可以记录当前的起始索引，也就是left指针所在位置
            while (valid == 0) {
                if (right - left + 1 == p.length()) res.add(left);
                // 如果左指针指的是有效字符,需要更改 window 中的 key 对应的 value
                // 如果有效字符对应的数量比目标子串少，说明无法匹配了
                if (map.containsKey(s.charAt(left))) {
                    window.put(s.charAt(left), window.get(s.charAt(left)) - 1);
                    if (window.get(s.charAt(left)) < map.get(s.charAt(left))) {
                        valid++;
                    }
                }
                left++;
            }
            right++;
        }
        return res;
    }
    
    /**
     * 滚动方法
     */
    public List<Integer> findAnagrams3(String s, String p) {
        List<Integer> res = new ArrayList<>();
        if (s.length() < p.length()) return res;
        int[] sCount = new int[26];
        int[] pCount = new int[26];
        for (int i = 0; i < p.length(); i++) {
            ++sCount[s.charAt(i) - 'a'];
            ++pCount[p.charAt(i) - 'a'];
        }
        if (Arrays.equals(sCount, pCount)) {
            res.add(0);
        }
        // 向前滚动
        for (int i = p.length(); i < s.length(); i++) {
            --sCount[s.charAt(i - p.length()) - 'a']; // 去头
            ++sCount[s.charAt(i) - 'a'];              // 加尾
            if (Arrays.equals(sCount, pCount)) {
                res.add(i - p.length() + 1);
            }
        }
        return res;
    }
    
}
