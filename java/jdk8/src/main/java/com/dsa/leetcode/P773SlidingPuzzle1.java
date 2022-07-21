package com.dsa.leetcode;

import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

/**
 * @link https://leetcode.cn/problems/sliding-puzzle/
 * 滑动谜题
 */
public class P773SlidingPuzzle1 {
    
    public static void main(String[] args) {
        P773SlidingPuzzle1 solution = new P773SlidingPuzzle1();
        int[][] board1 = {{1, 2, 3}, {4, 0, 5}};
        int res1 = solution.slidingPuzzle(board1);
        System.out.println(res1);
    }
    
    // 若 0 在第 0 个位置上可以与之交换的位置数组，即 0 可以与第 1、3 个元素交换位置
    int[][] swapRules = {{1, 3}, {0, 2, 4}, {1, 5}, {0, 4}, {1, 3, 5}, {2, 4}};
    
    /**
     * BFS
     * 将棋盘视作一个字符串，一个混乱的字符串遵循固有的交换规则，完成系列交换后得到一个有序的字符串即可。
     * 即将 board --> "1234560"
     */
    public int slidingPuzzle(int[][] board) {
        String endStr = "123450";
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 3; j++) {
                str.append(board[i][j]);
            }
        }
        if (endStr.equals(str.toString())) return 0;
        
        Set<String> visited = new HashSet<>();
        visited.add(str.toString());
        Deque<String> queue = new LinkedList<>();
        queue.offer(str.toString());
        int step = 0;
        while (!queue.isEmpty()) {
            ++step;
            for (int size = queue.size(); size > 0; --size) {
                char[] curCharArray = queue.poll().toCharArray();
                // 找到 0 在哪
                int zeroIndex = 0;
                for (int i = 0; i < curCharArray.length; ++i) {
                    if (curCharArray[i] == '0') {
                        zeroIndex = i;
                        break;
                    }
                }
                for (int i = 0; i < swapRules[zeroIndex].length; ++i) {
                    swap(curCharArray, zeroIndex, swapRules[zeroIndex][i]);
                    String newStr = new String(curCharArray);
                    if (endStr.equals(newStr)) return step; // 完成
                    if (!visited.contains(newStr)) {
                        visited.add(newStr);
                        queue.offer(newStr);
                    }
                    swap(curCharArray, zeroIndex, swapRules[zeroIndex][i]);
                }
            }
        }
        return -1;
    }
    
    private void swap(char[] charArray, int srcIndex, int targetIndex) {
        char t = charArray[srcIndex];
        charArray[srcIndex] = charArray[targetIndex];
        charArray[targetIndex] = t;
    }
}
