package com.dsa.leetcode;

import java.util.*;

/**
 * @link https://leetcode-cn.com/problems/letter-combinations-of-a-phone-number/
 * 电话号码的字母组合
 */
public class P17LetterCombinationsOfAPhoneNumber1 {
    
    public static void main(String[] args) {
    
    }
    
    Map<Character, String> map = new HashMap<Character, String>() {{
        put('2', "abc");
        put('3', "def");
        put('4', "ghi");
        put('5', "jkl");
        put('6', "mno");
        put('7', "pqrs");
        put('8', "tuv");
        put('9', "wxyz");
    }};
    
    /*
     digits = 23
     2 = [a, b, c], 3 = [d, e, f]
     */
    public List<String> letterCombinations(String digits) {
        List<String> res = new LinkedList<>();
        if (digits.length() == 0) {
            return res;
        }
        StringBuffer path = new StringBuffer();
        dfs(digits, 0, path, res);
        return res;
    }
    
    public void dfs(String digits, int cur, StringBuffer path, List<String> res) {
        if (cur == digits.length()) {
            res.add(new String(path));
            return;
        }
        char digit = digits.charAt(cur);
        String letters = map.get(digit);
        for (int i = 0; i < letters.length(); i++) {
            path.append(letters.charAt(i));
            dfs(digits, cur + 1, path, res);
            path.deleteCharAt(cur); // 回溯
        }
    }
}
