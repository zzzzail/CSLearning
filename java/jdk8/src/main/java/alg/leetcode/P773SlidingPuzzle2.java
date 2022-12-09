package alg.leetcode;

import java.util.*;

public class P773SlidingPuzzle2 {
    
    public static void main(String[] args) {
        P773SlidingPuzzle2 solution = new P773SlidingPuzzle2();
        int[][] board1 = {{3, 2, 4}, {1, 5, 0}};
        int res1 = solution.slidingPuzzle(board1);
        System.out.println(res1);

        int[][] board2 = {{5, 3, 2}, {0, 4, 1}};
        int res2 = solution.slidingPuzzle(board2);
        System.out.println(res2);
    }
    
    int[][] moves = {{1, 3}, {0, 2, 4}, {1, 5}, {0, 4}, {1, 3, 5}, {2, 4}};
    
    /**
     * A* 算法
     */
    public int slidingPuzzle(int[][] board) {
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
    
    void swap(char[] arr, int a, int b) {
        char tmp = arr[a];
        arr[a] = arr[b];
        arr[b] = tmp;
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
