package com.java.dsa.leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * 无重复字符的最长子串
 *
 * @author zail
 * @link <a href="https://leetcode.cn/problems/longest-substring-without-repeating-characters/">无重复字符的最长子串</a>
 * @date 2022/5/27
 */
public class P3LongestSubstringWithoutRepeatingCharacters1 {
    
    public static void main(String[] args) {
        P3LongestSubstringWithoutRepeatingCharacters1 solution = new P3LongestSubstringWithoutRepeatingCharacters1();
        int res1 = solution.lengthOfLongestSubstring2("abcabcbb");
        System.out.println(res1);
        
        int res2 = solution.lengthOfLongestSubstring2("bbbbb");
        System.out.println(res2);
        
        int res3 = solution.lengthOfLongestSubstring2("pwwkew");
        System.out.println(res3);
    }
    
    /**
     * 滑动窗口方式
     * 时间复杂度： O(n^2)
     */
    public int lengthOfLongestSubstring(String s) {
        int n = s.length();
        if (n <= 1) return s.length();
        
        int left = 0, right = 1;
        int maxVal = 0;
        while (left < n && right < n) {
            char cur = s.charAt(right);
            for (int i = right - 1; i >= left; --i) {
                if (cur == s.charAt(i)) {
                    left = i + 1;
                    break;
                }
            }
            ++right;
            maxVal = Math.max(maxVal, right - left);
        }
        return maxVal;
    }
    
    /**
     * 滑动窗口可以用 HashSet 加速
     * 还是 O(n ^ 2) 的算法，只快了 2 ms
     */
    public int lengthOfLongestSubstring2(String s) {
        int n = s.length();
        if (n <= 1) return s.length();
        
        int maxVal = 0;
        Set<Character> set = new HashSet<>();
        set.add(s.charAt(0));
        int left = 0, right = 1;
        while (left < n && right < n) {
            char cur = s.charAt(right);
            // 如果 set 中有这个字符，则从 left 开始向前查找到相同的字符
            if (set.contains(cur)) {
                for (int i = left; i < right; i++) {
                    set.remove(s.charAt(i));
                    if (cur == s.charAt(i)) {
                        left = i + 1;
                        break;
                    }
                }
            }
            else {
                set.add(cur);
                ++right;
            }
            maxVal = Math.max(maxVal, right - left);
        }
        return maxVal;
    }
    
    /**
     * 这个算法可以学一学
     */
    public int lengthOfLongestSubstring3(String s) {
        int i = 0, flag = 0, res = 0;
        while (i < s.length()) {
            // 通过 string 的 api
            int temp = s.indexOf(s.charAt(i), flag);
            if (temp >= flag && temp < i) {
                if (i - flag > res) res = i - flag;
                flag = temp + 1;
            }
            else if (i - flag + 1 > res) res = i - flag + 1;
            i++;
        }
        return res;
    }
}
