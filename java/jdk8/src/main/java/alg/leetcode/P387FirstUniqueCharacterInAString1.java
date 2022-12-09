package alg.leetcode;

import java.util.HashMap;
import java.util.Map;

public class P387FirstUniqueCharacterInAString1 {
    
    public static void main(String[] args) {
    
    }
    
    /**
     * 用 map 方法
     */
    public int firstUniqChar(String s) {
        if (s == null || s.length() == 0) return -1;
        if (s.length() == 1) return 0;
        
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); ++i) {
            if (map.containsKey(s.charAt(i))) {
                map.put(s.charAt(i), map.get(s.charAt(i)) + 1);
            }
            else {
                map.put(s.charAt(i), 1);
            }
        }
        for (int i = 0; i < s.length(); ++i) {
            if (map.get(s.charAt(i)) == 1) return i;
        }
        return -1;
    }
    
    // 奇技淫巧
    public int firstUniqChar2(String s) {
        int res = s.length();
        for (char i = 'a'; i <= 'z' + 1; i++) {
            if (s.indexOf(i) == -1) continue;
            if (s.indexOf(i) == s.lastIndexOf(i)) res = Math.min(res, s.indexOf(i));
        }
        return res != s.length() ? res : -1;
    }
    
    // Java API
    public int firstUniqChar3(String s) {
        for (int i = 0; i < s.length(); i++) {
            if (s.indexOf(s.charAt(i)) == s.lastIndexOf(s.charAt(i))) {
                return i;
            }
        }
        return -1;
    }
}
