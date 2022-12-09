package alg.leetcode;

import java.util.*;

public class P433MinimumGeneticMutation2 {
    
    public static void main(String[] args) {
    
    }
    
    public int minMutation(String start, String end, String[] bank) {
        Set<String> bankSet = new HashSet<>(Arrays.asList(bank));
        if (!bankSet.contains(end)) return -1;
        bankSet.remove(start);
        
        char[] fourGenetic = new char[]{'A', 'C', 'G', 'T'};
        Deque<String> queue = new LinkedList<>();
        queue.add(start);
        int step = 0;
        while (!queue.isEmpty()) {
            ++step;
            for (int size = queue.size(); size > 0; --size) {
                char[] curCharArray = queue.poll().toCharArray();
                for (int i = 0; i < curCharArray.length; ++i) {
                    char old = curCharArray[i];
                    for (char replace : fourGenetic) {
                        if (old == replace) continue;
                        curCharArray[i] = replace;
                        String newGenetic = new String(curCharArray);
                        if (end.equals(newGenetic)) return step; // 结束
                        else if (bankSet.contains(newGenetic)) {
                            queue.add(newGenetic);
                            bankSet.remove(newGenetic);
                        }
                    }
                    curCharArray[i] = old;
                }
            }
        }
        return -1;
    }
}
