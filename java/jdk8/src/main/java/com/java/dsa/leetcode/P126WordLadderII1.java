package com.java.dsa.leetcode;

import java.util.*;

/**
 * @link https://leetcode-cn.com/problems/word-ladder-ii/
 * 单词接龙 2
 */
public class P126WordLadderII1 {
    
    public static void main(String[] args) {
        String beginWord1 = "hit";
        String endWord1 = "cog";
        List<String> wordList1 = new ArrayList<>(Arrays.asList("hot", "dot", "dog", "lot", "log", "cog"));
        List<List<String>> res1 = findLadders(beginWord1, endWord1, wordList1);
        System.out.println(res1);
        
        String beginWord2 = "a";
        String endWord2 = "c";
        List<String> wordList2 = new ArrayList<>(Arrays.asList("a", "b", "c"));
        List<List<String>> res2 = findLadders(beginWord2, endWord2, wordList2);
        System.out.println(res2);
        
        String beginWord3 = "red";
        String endWord3 = "tax";
        List<String> wordList3 = new ArrayList<>(Arrays.asList("ted", "tex", "red", "tax", "tad", "den", "rex", "pee"));
        List<List<String>> res3 = findLadders(beginWord3, endWord3, wordList3);
        // 预期结果 [["red","ted","tad","tax"],["red","ted","tex","tax"],["red","rex","tex","tax"]]
        System.out.println(res3);
    }
    
    /**
     * ["hit","hot","dot","dog","cog"]
     * ["hit","hot","lot","log","cog"]
     * 使用 尾 Map 这个想法非常失败！
     * Set 里的值是不能删除的！
     */
    public static List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        Set<String> set = new HashSet<>(wordList);
        List<List<String>> res = new LinkedList<>();
        if (!set.contains(endWord)) return res;
        set.remove(beginWord);
        
        Queue<String> queue = new LinkedList<>();
        queue.add(beginWord);
        // 已尾单词作为 key 的 map
        Map<String, List<String>> tailMap = new HashMap<>();
        tailMap.put(beginWord, new ArrayList<>(Collections.singletonList(beginWord)));
        while (!queue.isEmpty()) {
            for (int count = queue.size(); count > 0; --count) {
                String currentWord = queue.poll();
                List<String> diffs = getALetterDifferences(set, currentWord);
                if (diffs.size() <= 0) continue;
                for (String diff : diffs) {
                    // if (endWord.equals(diff)) break; // 到达 endWord
                    set.remove(diff);
                    queue.add(diff);
                    updateTailMap(tailMap, currentWord, diff);
                }
                tailMap.remove(currentWord);
            }
        }
        handleResult(tailMap, res, endWord);
        return res;
    }
    
    private static void handleResult(Map<String, List<String>> tailMap, List<List<String>> res, String endWord) {
        int min = Integer.MAX_VALUE; // 最短路径是多少步
        for (String key : tailMap.keySet()) {
            List<String> list = tailMap.get(key);
            if (countDiffLetter(endWord, key) == 0) min = Math.min(min, list.size());
            else if (countDiffLetter(endWord, key) == 1) min = Math.min(min, list.size() + 1);
        }
        for (String key : tailMap.keySet()) {
            List<String> list = tailMap.get(key);
            int c = countDiffLetter(endWord, key);
            if (c == 0) { // 相等的情况
                min = list.size();
                res.add(new ArrayList<>(list));
            }
            else if (c == 1 && list.size() < min) { // 只差一个字母的情况
                List<String> l = tailMap.get(key);
                l.add(endWord);
                res.add(new ArrayList<>(l));
            }
        }
    }
    
    private static List<String> getALetterDifferences(Set<String> set, String str) {
        List<String> res = new ArrayList<>();
        for (String s : set)
            if (countDiffLetter(str, s) == 1) res.add(s);
        return res;
    }
    
    private static int countDiffLetter(String str1, String str2) {
        if (str1 == null || str2 == null) return -1;
        char[] ca1 = str1.toCharArray();
        char[] ca2 = str2.toCharArray();
        int min = Math.min(ca1.length, ca2.length);
        int diff = Math.abs(ca1.length < ca2.length ? ca2.length - ca1.length : ca1.length - ca2.length);
        int res = 0;
        for (int i = 0; i < min; ++i) {
            if (ca1[i] != ca2[i]) ++res;
        }
        return res + diff;
    }
    
    private static void updateTailMap(Map<String, List<String>> tailMap, String oldTail, String newTail) {
        List<String> oldList = tailMap.get(oldTail);
        if (oldList == null) return;
        List<String> newList = new ArrayList<>(oldList);
        newList.add(newTail);
        tailMap.put(newTail, newList);
    }
}
