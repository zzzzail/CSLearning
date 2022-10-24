package com.java.dsa.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhangxq
 * @since 2022/10/24
 */
public class P3LongestSubstringWithoutRepeatingCharacters02 {
    
    public static void main(String[] args) {
    
    }
    
    /**
     * 双指针法
     * i 指针在 j 指针之前
     */
    public int lengthOfLongestSubstring(String s) {
        // val 存储字符出现的次数
        Map<Character, Integer> heap = new HashMap<>();
        int max = 0;
        for (int i = 0, j = 0; i < s.length(); i++) {
            heap.put(s.charAt(i), heap.getOrDefault(s.charAt(i), 0) + 1);
            // 如果第 i 个字符出现的次数超过 1 次，说明 j 指针需要往前走了
            while (heap.get(s.charAt(i)) > 1) {
                heap.put(s.charAt(j), heap.get(s.charAt(j)) - 1);
                j++;
            }
            max = Math.max(max, i - j + 1);
        }
        return max;
    }
}

