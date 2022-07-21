package com.dsa.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @link https://leetcode-cn.com/problems/sudoku-solver/
 * 解数独
 */
public class P37SudokuSolver1 {
    
    private boolean[][] row = new boolean[9][9];
    private boolean[][] col = new boolean[9][9];
    private boolean[][][] block = new boolean[3][3][9];
    private boolean done = false;
    // 标记哪些空需要填
    private List<int[]> spaces = new ArrayList<>();
    
    public void solveSudoku(char[][] board) {
        for (int i = 0; i < 9; ++i) {
            for (int j = 0; j < 9; ++j) {
                if (board[i][j] == '.') {
                    spaces.add(new int[]{i, j});
                }
                else {
                    int digit = board[i][j] - '1';
                    row[i][digit] = col[j][digit] = block[i / 3][j / 3][digit] = true;
                }
            }
        }
        dfs(board, 0);
    }
    
    public void dfs(char[][] board, int pos) {
        if (pos == spaces.size()) {
            done = true; // 标记是否已完成填写
            return;
        }
        
        int[] space = spaces.get(pos);
        int i = space[0], j = space[1];
        for (int digit = 0; digit < 9 && !done; ++digit) {
            if (!row[i][digit] && !col[j][digit] && !block[i / 3][j / 3][digit]) {
                row[i][digit] = col[j][digit] = block[i / 3][j / 3][digit] = true;
                board[i][j] = (char) (digit + '1');
                dfs(board, pos + 1);
                row[i][digit] = col[j][digit] = block[i / 3][j / 3][digit] = false;
            }
        }
    }
    
}
