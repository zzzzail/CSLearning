package alg.leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 无重复字符的最长子串
 *
 * @author zail
 * @link <a href="https://leetcode.cn/problems/longest-substring-without-repeating-characters/">无重复字符的最长子串</a>
 * @date 2022/5/27
 */
public class P3LongestSubstringWithoutRepeatingCharacters {
    
    public static void main(String[] args) {
        P3LongestSubstringWithoutRepeatingCharacters solution = new P3LongestSubstringWithoutRepeatingCharacters();
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
     * 双指针法
     * i 指针在 j 指针之前
     */
    public int lengthOfLongestSubstring3(String s) {
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
    
    /**
     * 这个算法可以学一学
     */
    public int lengthOfLongestSubstring4(String s) {
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
