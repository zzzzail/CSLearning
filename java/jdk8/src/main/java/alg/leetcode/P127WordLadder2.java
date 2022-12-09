package alg.leetcode;

import java.util.*;

public class P127WordLadder2 {
    
    public static void main(String[] args) {
        P127WordLadder2 solution = new P127WordLadder2();
        int res1 = solution.ladderLength2("hit", "cog", Arrays.asList("hot", "dot", "dog", "lot", "log", "cog"));
        System.out.println(res1);
    }
    
    /**
     * BFS
     */
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> wordSet = new HashSet<>(wordList);
        if (!wordSet.contains(endWord)) return 0;
        wordSet.remove(beginWord);
        
        Deque<String> queue = new LinkedList<>();
        queue.offer(beginWord);
        int step = 0;
        while (!queue.isEmpty()) {
            ++step;
            for (int count = queue.size(); count > 0; --count) {
                String curWord = queue.poll();
                if (endWord.equals(curWord)) return step;
                
                Iterator<String> iterator = wordSet.iterator();
                while (iterator.hasNext()) {
                    String w = iterator.next();
                    if (diff(curWord, w) == 1) {
                        queue.offer(w);
                        iterator.remove();
                    }
                }
            }
        }
        return 0;
    }
    
    private int diff(String word1, String word2) {
        if (word1 == null || word2 == null) return -1;
        char[] charArray1 = word1.toCharArray();
        char[] charArray2 = word2.toCharArray();
        int min = Math.min(charArray1.length, charArray2.length);
        int diff = Math.abs(charArray1.length - charArray2.length);
        int res = 0;
        for (int i = 0; i < min; i++) {
            if (charArray1[i] != charArray2[i]) ++res;
        }
        return res + diff;
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
    
}
