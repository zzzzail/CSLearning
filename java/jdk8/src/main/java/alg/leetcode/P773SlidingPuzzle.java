package alg.leetcode;

import java.util.*;

/**
 * @link https://leetcode.cn/problems/sliding-puzzle/
 * 滑动谜题
 */
public class P773SlidingPuzzle {
    
    public static void main(String[] args) {
        P773SlidingPuzzle solution = new P773SlidingPuzzle();
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
    
    int[][] moves = {{1, 3}, {0, 2, 4}, {1, 5}, {0, 4}, {1, 3, 5}, {2, 4}};
    
    /**
     * A* 算法
     */
    public int slidingPuzzle2(int[][] board) {
        String endStr = "123450";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 2; i++)
            for (int j = 0; j < 3; j++)
                sb.append(board[i][j]);
        if (endStr.equals(sb.toString())) return 0;
        String boardStr = sb.toString();
        
        Queue<BoardString> queue = new PriorityQueue<>((bs1, bs2) -> bs1.getScore() - bs2.getScore());
        queue.offer(new BoardString(boardStr));
        Set<String> visited = new HashSet<>();
        visited.add(boardStr);
        while (!queue.isEmpty()) {
            BoardString cur = queue.poll();
            char[] curCharArray = cur.getStr().toCharArray();
            int step = cur.getStep();
            for (int nextZeroPos : moves[cur.getZeroIndex()]) {
                swap(curCharArray, cur.getZeroIndex(), nextZeroPos);
                String newStr = new String(curCharArray);
                if (endStr.equals(newStr)) return step + 1; // 找到了
                if (!visited.contains(newStr)) {
                    queue.offer(new BoardString(newStr, nextZeroPos, step + 1, step + 1 + f2(newStr, endStr)));
                    visited.add(newStr);
                }
                swap(curCharArray, cur.getZeroIndex(), nextZeroPos);
            }
        }
        return -1;
    }
    
    /**
     * 曼哈顿距离
     * 坐标差之和
     * |x1 - x2| + |y1 - y2|
     */
    public int f(String from, String destination) {
        int distance = 0;
        for (int i = 0; i < 6; i++) {
            int v = from.charAt(i) - '0' - 1;
            // 曼哈顿距离，计算每个坐标的当前位置与最终位置的距离
            // -1 / 3 = 0; -1 % 3 = -1 本应该在 0 行 -1 列
            distance += Math.abs(v / 3 - i / 3) + Math.abs(v % 3 - i % 3);
        }
        return distance;
    }
    
    /**
     * 汉明距离 Hamming
     * 两个字符串不一样的字符有几个
     */
    public int f2(String from, String destination) {
        int distance = 0;
        int n = Math.min(from.length(), destination.length());
        for (int i = 0; i < n; ++i) {
            if (from.charAt(i) != destination.charAt(i)) ++distance;
        }
        return distance;
    }
    
    static class BoardString {
        private String str;
        private int zeroIndex;
        private int step; // 记录走了多少步
        private int score;
        
        public BoardString(String str) {
            this.str = str;
            this.zeroIndex = -1;
            this.step = 0;
            this.score = 0;
        }
        
        public BoardString(String str, int zeroIndex, int step, int score) {
            this.str = str;
            this.zeroIndex = zeroIndex;
            this.step = step;
            this.score = score;
        }
        
        public String getStr() {
            return str;
        }
        
        public void setStr(String str) {
            this.str = str;
        }
        
        public int getZeroIndex() {
            if (zeroIndex != -1) return zeroIndex;
            for (int i = 0; i < str.length(); i++) {
                if (str.charAt(i) == '0') zeroIndex = i;
            }
            return zeroIndex;
        }
        
        public void setZeroIndex(int zeroIndex) {
            this.zeroIndex = zeroIndex;
        }
        
        public int getStep() {
            return step;
        }
        
        public void setStep(int step) {
            this.step = step;
        }
        
        public int getScore() {
            return score;
        }
        
        public void setScore(int score) {
            this.score = score;
        }
    }
}
