package com.java.dsa.leetcode;

public class P36ValidSudoku2 {
    
    public boolean isValidSudoku(char[][] board) {
        int n = 9;
        boolean[][] row = new boolean[n][n];
        boolean[][] col = new boolean[n][n];
        boolean[][][] block = new boolean[3][3][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                char c = board[i][j];
                if (c == '.') continue;
                int u = c - '0' - 1;
                if (row[i][u] || col[j][u] || block[i / 3][j / 3][u]) return false;
                row[i][u] = col[j][u] = block[i / 3][j / 3][u] = true;
            }
        }
        return true;
    }
    
}
