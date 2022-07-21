package com.dsa.leetcode;

import java.util.*;

public class P127WordLadder3 {
    
    /**
     * 双向 BFS + 队列少先执行优化
     * 100 ms 左右
     */
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        int end = wordList.indexOf(endWord);
        if (end == -1) return 0;
        wordList.add(beginWord);
        int start = wordList.size() - 1;
        Set<Integer> visited1 = new HashSet<>();
        Set<Integer> visited2 = new HashSet<>();
        visited1.add(start);
        visited2.add(end);
        Queue<Integer> queue1 = new LinkedList<>();
        Queue<Integer> queue2 = new LinkedList<>();
        queue1.offer(start);
        queue2.offer(end);
        int count = 0;
        while (!queue1.isEmpty() && !queue2.isEmpty()) {
            count++;
            // 优化这里 队列中元素少的先执行
            if (queue1.size() > queue2.size()) {
                // 交换队列
                Queue<Integer> tmp = queue1;
                queue1 = queue2;
                queue2 = tmp;
                // 交换 visited
                Set<Integer> t = visited1;
                visited1 = visited2;
                visited2 = t;
            }
            for (int size = queue1.size(); size > 0; --size) {
                String s = wordList.get(queue1.poll());
                for (int i = 0; i < wordList.size(); ++i) {
                    if (visited1.contains(i)) continue;
                    if (!canConvert(s, wordList.get(i))) continue;
                    // 结束条件
                    if (visited2.contains(i)) return count + 1;
                    visited1.add(i);
                    queue1.offer(i);
                }
            }
        }
        return 0;
    }
    
    public boolean canConvert(String a, String b) {
        int count = 0;
        for (int i = 0; i < a.length(); ++i)
            if (a.charAt(i) != b.charAt(i))
                if (++count > 1) return false;
        return count == 1;
    }
    
}
