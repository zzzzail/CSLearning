package com.java.dsa.leetcode;

import java.util.*;

/**
 * @link https://leetcode.cn/problems/group-anagrams/
 * 字母异位词的分组
 */
public class P49GroupAnagrams1 {
    
    public static void main(String[] args) {
    
    }
    
    /**
     * 先排序后 hash 的方式
     */
    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> res = new ArrayList<>();
        if (strs.length == 0) return res;
        Map<String, List<String>> group = new HashMap<>();
        for (String str : strs) {
            char[] arr = str.toCharArray();
            Arrays.sort(arr); // 异位词排序后相同
            String key = new String(arr);
            if (group.containsKey(key)) {
                group.get(key).add(str);
            }
            else {
                List<String> val = new ArrayList<>();
                val.add(str);
                group.put(key, val);
            }
        }
        for (String key : group.keySet()) {
            res.add(group.get(key));
        }
        return res;
    }
    
}
