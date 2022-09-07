package com.java.dsa.leetcode;

import java.util.*;

/**
 * @link https://leetcode-cn.com/problems/minimum-genetic-mutation/
 * 最小基因变化
 */
public class P433MinimumGeneticMutation1 {
    
    public static void main(String[] args) {
        P433MinimumGeneticMutation1 solution = new P433MinimumGeneticMutation1();
        
        String start1 = "AACCGGTT";
        String end1 = "AACCGGTA";
        String[] bank1 = new String[]{"AACCGGTA"};
        System.out.println(solution.minMutation2(start1, end1, bank1));
        
        String start2 = "AACCGGTT";
        String end2 = "AAACGGTA";
        String[] bank2 = new String[]{"AACCGGTA", "AACCGCTA", "AAACGGTA"};
        System.out.println(solution.minMutation2(start2, end2, bank2));
        
        String start3 = "AAAAACCC";
        String end3 = "AACCCCCC";
        String[] bank3 = new String[]{"AAAACCCC", "AAACCCCC", "AACCCCCC"};
        System.out.println(solution.minMutation2(start3, end3, bank3));
    }
    
    /**
     * 这就是 BFS 的题，因为遍历所有条件之后记录遍历的层数
     */
    public int minMutation(String start, String end, String[] bank) {
        Set<String> set = new HashSet<>(Arrays.asList(bank));
        // 如果结果 end 不在基因库里
        if (!set.contains(end)) return -1;
        set.remove(start); // 从基因库中删除 start
        
        char[] four = {'A', 'C', 'G', 'T'};
        Queue<String> queue = new LinkedList<>();
        queue.offer(start);
        int step = 0;
        while (!queue.isEmpty()) {
            step++;
            for (int count = queue.size(); count > 0; --count) {
                char[] cur = queue.poll().toCharArray();
                for (int i = 0; i < cur.length; ++i) {
                    char oldChar = cur[i];
                    for (char c : four) {
                        cur[i] = c;
                        String newGenetic = new String(cur);
                        if (end.equals(newGenetic)) return step; // 找到了
                            // 如果 set 中有新的基因值，则加入队列
                        else if (set.contains(newGenetic)) {
                            queue.offer(newGenetic); // 加入队列
                            set.remove(newGenetic);  // 从 set 中删除该基因值是为了更好的搜索
                        }
                    }
                    cur[i] = oldChar;
                }
            }
        }
        return -1;
    }
    
    /**
     * 双向 BFS
     */
    public int minMutation2(String start, String end, String[] bank) {
        Set<String> bankSet = new HashSet<>(Arrays.asList(bank));
        if (!bankSet.contains(end)) return -1;
        
        char[] four = {'A', 'C', 'G', 'T'};
        Set<String> visited1 = new HashSet<>();
        Set<String> visited2 = new HashSet<>();
        visited1.add(start);
        visited2.add(end);
        Queue<String> queue1 = new LinkedList<>();
        Queue<String> queue2 = new LinkedList<>();
        queue1.offer(start);
        queue2.offer(end);
        int step = 0;
        while (!queue1.isEmpty() && !queue2.isEmpty()) {
            ++step;
            if (queue2.size() > queue1.size()) { // 换
                Queue<String> tmpQueue = queue1;
                queue1 = queue2;
                queue2 = tmpQueue;
                Set<String> tmpSet = visited1;
                visited1 = visited2;
                visited2 = tmpSet;
            }
            for (int size = queue1.size(); size > 0; --size) {
                char[] cur = queue1.poll().toCharArray();
                for (int i = 0; i < cur.length; ++i) {
                    char old = cur[i];
                    for (char replace : four) {
                        if (old == replace) continue;
                        cur[i] = replace;
                        String newGenetic = new String(cur);
                        if (!bankSet.contains(newGenetic)) continue;
                        if (visited1.contains(newGenetic)) continue;
                        if (visited2.contains(newGenetic)) return step; // 结束条件
                        queue1.offer(newGenetic);
                        visited1.add(newGenetic);
                    }
                    cur[i] = old;
                }
            }
        }
        return -1;
    }
    
}
