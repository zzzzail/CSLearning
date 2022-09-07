package com.java.dsa.leetcode.jzof;

/**
 * 矩阵中的路径
 *
 * @author zail
 * @link https://leetcode.cn/problems/ju-zhen-zhong-de-lu-jing-lcof/
 * @date 2022/7/19
 */
public class P12 {
    
    public static void main(String[] args) {
        P12 solution = new P12();
        
        char[][] board1 = {
                {'A', 'B', 'C', 'E'},
                {'S', 'F', 'C', 'S'},
                {'A', 'D', 'E', 'E'}
        };
        System.out.println(solution.exist(board1, "ABCCED"));
    }
    
    public boolean exist(char[][] board, String word) {
        int m = board.length, n = board[0].length;
        boolean[][] visited = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (dfs(board, visited, i, j, word, 0)) return true;
            }
        }
        return false;
    }
    
    private boolean dfs(char[][] board, boolean[][] visited, int row, int col, String word, int i) {
        // 匹配到单词的结尾就算成功
        if (i >= word.length()) return true;
        char c = word.charAt(i);
        if (!inArea(board, row, col) || visited[row][col] || c != board[row][col]) return false;
        
        // 设置已访问
        visited[row][col] = true;
        boolean up = dfs(board, visited, row - 1, col, word, i + 1);
        boolean left = dfs(board, visited, row, col - 1, word, i + 1);
        boolean down = dfs(board, visited, row + 1, col, word, i + 1);
        boolean right = dfs(board, visited, row, col + 1, word, i + 1);
        visited[row][col] = false; // 回溯
        
        return up || left || down || right;
    }
    
    private boolean inArea(char[][] board, int row, int col) {
        return 0 <= row && row < board.length && 0 <= col && col < board[0].length;
    }
}
