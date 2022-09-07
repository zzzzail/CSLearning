package com.java.dsa.leetcode;

import java.util.*;

/**
 * @link https://leetcode-cn.com/problems/word-ladder/description/
 * 单词接龙
 */
public class P127WordLadder1 {
    
    public static void main(String[] args) {
        String beginWord1 = "hit";
        String endWord1 = "cog";
        List<String> wordList1 = new ArrayList<>(Arrays.asList("hot", "dot", "dog", "lot", "log", "cog"));
        int res1 = ladderLength(beginWord1, endWord1, wordList1);
        System.out.println(res1);
        
    }
    
    private static int diff(String word1, String word2) {
        if (word1 == null || word2 == null) return -1;
        char[] charArray1 = word1.toCharArray();
        char[] charArray2 = word2.toCharArray();
        int min = Math.min(charArray1.length, charArray2.length);
        int diff = Math.abs(charArray1.length - charArray2.length);
        int res = 0;
        for (int i = 0; i < min; ++i) {
            if (charArray1[i] != charArray2[i]) ++res;
        }
        return res + diff;
    }
    
    /**
     * 使用 BFS 实现
     */
    public static int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> set = new HashSet<>(wordList);
        if (!set.contains(endWord)) return 0; // set 中没有最终结果
        set.remove(beginWord);
        
        Queue<String> queue = new LinkedList<>();
        queue.offer(beginWord);
        int step = 0;
        while (!queue.isEmpty()) {
            ++step;
            for (int count = queue.size(); count > 0; --count) {
                String currentWord = queue.poll();
                if (endWord.equals(currentWord)) return step;
                
                Iterator<String> iterator = set.iterator();
                while (iterator.hasNext()) {
                    String s = iterator.next();
                    if (diff(currentWord, s) == 1) {
                        queue.offer(s);
                        iterator.remove(); // 删除 set 中的元素
                    }
                }
            }
        }
        return 0;
    }
    
    /**
     * 双向 BFS
     * 分别从起点和终点执行 BFS，直到遍历的部分有交集。
     */
    public int ladderLength2(String beginWord, String endWord, List<String> wordList) {
        Set<String> beginWordSet = new HashSet<>(wordList);
        if (!beginWordSet.contains(endWord)) return 0;
        beginWordSet.remove(beginWord);
        Set<String> endWordSet = new HashSet<>(wordList);
        endWordSet.remove(endWord);
        
        Deque<String> beginQueue = new LinkedList<>();
        beginQueue.offer(beginWord);
        Deque<String> endQueue = new LinkedList<>();
        endQueue.offer(endWord);
        int count1 = 0, count2 = 0;
        while (!beginQueue.isEmpty() && !endQueue.isEmpty()) {
            ++count1;
            for (int count = beginQueue.size(); count > 0; --count) {
                String curWord = beginQueue.poll();
                Iterator<String> iterator = beginWordSet.iterator();
                while (iterator.hasNext()) {
                    String w = iterator.next();
                    if (diff(curWord, w) != 1) continue;
                    if (!endWordSet.contains(w)) {
                        return count1 + count2 + 1;
                    }
                    beginQueue.offer(w);
                    iterator.remove();
                }
            }
            ++count2;
            for (int count = endQueue.size(); count > 0; --count) {
                String curWord = endQueue.poll();
                Iterator<String> iterator = endWordSet.iterator();
                while (iterator.hasNext()) {
                    String w = iterator.next();
                    if (diff(curWord, w) != 1) continue;
                    if (!beginWordSet.contains(w)) {
                        return count1 + count2 + 1;
                    }
                    endQueue.offer(w);
                    iterator.remove();
                }
            }
        }
        return 0;
    }
    
    /**
     * 双向 BFS
     */
    public int ladderLength3(String beginWord, String endWord, List<String> wordList) {
        if (!wordList.contains(endWord)) return 0;
        
        Set<String> beginWordVisited = new HashSet<>();
        beginWordVisited.add(beginWord);
        Set<String> endWordVisited = new HashSet<>();
        endWordVisited.add(endWord);
        Deque<String> beginQueue = new LinkedList<>();
        beginQueue.offer(beginWord);
        Deque<String> endQueue = new LinkedList<>();
        endQueue.offer(endWord);
        int count1 = 0, count2 = 0;
        while (!beginQueue.isEmpty() && !endQueue.isEmpty()) {
            ++count1;
            for (int count = beginQueue.size(); count > 0; --count) {
                String curWord = beginQueue.poll();
                for (String w : wordList) {
                    if (beginWordVisited.contains(w)) continue;
                    if (diff(curWord, w) != 1) continue;
                    if (endWordVisited.contains(w)) { // 结束条件
                        return count1 + count2 + 1;
                    }
                    beginQueue.offer(w);
                    beginWordVisited.add(w);
                }
            }
            ++count2;
            for (int count = endQueue.size(); count > 0; --count) {
                String curWord = endQueue.poll();
                for (String w : wordList) {
                    if (endWordVisited.contains(w)) continue;
                    if (diff(curWord, w) != 1) continue;
                    if (beginWordVisited.contains(w)) { // 结束条件
                        return count1 + count2 + 1;
                    }
                    endQueue.offer(w);
                    endWordVisited.add(w);
                }
            }
        }
        return 0;
    }
    
    /**
     * 双向 BFS + 队列少先执行优化
     * 100 ms 左右
     */
    public int ladderLength4(String beginWord, String endWord, List<String> wordList) {
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
