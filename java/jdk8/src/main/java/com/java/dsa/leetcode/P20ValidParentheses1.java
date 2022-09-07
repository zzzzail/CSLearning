package com.java.dsa.leetcode;

import java.util.*;

/**
 * @link https://leetcode-cn.com/problems/valid-parentheses/
 * 有效的括号
 */
public class P20ValidParentheses1 {
    
    public static void main(String[] args) {
        boolean res1 = isValid("(){}[]");
        System.out.println(res1);
        
        boolean res2 = isValid("(){}]");
        System.out.println(res2);
    
        boolean res3 = isValid("({}[]");
        System.out.println(res3);
    }
    
    public static boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        String l = "([{";
        String r = ")]}";
        for (int i = 0; i < s.length(); i++) {
            if (l.contains("" + s.charAt(i))) {
                stack.push(s.charAt(i));
            }
            if (r.contains("" + s.charAt(i))) {
                if (stack.size() == 0) return false;
                Character c = stack.pop();
                if (!match(c, s.charAt(i))) {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }
    
    public static boolean match(Character c1, Character c2) {
        switch (c1){
            case '(':
                return c2.equals(')');
            case '[':
                return c2.equals(']');
            case '{':
                return c2.equals('}');
            default:
                return false;
        }
    }
    
    public static boolean isValid2(String s) {
        int n = s.length();
        if (n % 2 == 1) { return false; }
    
        Map<Character, Character> pairs = new HashMap<Character, Character>() {{
            put(')', '(');
            put(']', '[');
            put('}', '{');
        }};
        Deque<Character> stack = new LinkedList<Character>();
        for (int i = 0; i < n; i++) {
            char ch = s.charAt(i);
            if (pairs.containsKey(ch)) {
                if (stack.isEmpty() || stack.peek() != pairs.get(ch)) {
                    return false;
                }
                stack.pop();
            } else {
                stack.push(ch);
            }
        }
        return stack.isEmpty();
    }
    
}
