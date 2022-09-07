package com.java.dsa.leetcode;

import java.util.ArrayList;
import java.util.List;

public class P51NQueues2 {
    
    public static void main(String[] args) {
        P51NQueues2 solution = new P51NQueues2();
        List<List<String>> res1 = solution.solveNQueens(4);
        System.out.println(res1);
    }
    
    /**
     * N 皇后问题，返回所有皇后可能放置的棋盘
     */
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> res = new ArrayList<>();
        if (n == 0) return res;
        // 初始化棋盘
        char[][] chessboard = new char[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                chessboard[i][j] = '.';
            }
        }
        backtrack(n, 0, chessboard, res);
        return res;
    }
    
    private void backtrack(int n, int row, char[][] chessboard, List<List<String>> res) {
        if (row == n) {
            res.add(toList(chessboard));
            return;
        }
        for (int col = 0; col < n; col++) {
            if (isValid(n, chessboard, row, col)) {
                chessboard[row][col] = 'Q';
                backtrack(n, row + 1, chessboard, res);
                chessboard[row][col] = '.';
            }
        }
    }
    
    private boolean isValid(int n, char[][] chessboard, int row, int col) {
        char Q = 'Q';
        for (int i = 0; i < row; i++) {
            if (chessboard[i][col] == Q) return false;
        }
        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; --i, --j) {
            if (chessboard[i][j] == Q) return false;
        }
        for (int i = row - 1, j = col + 1; i >= 0 && j < n; --i, ++j) {
            if (chessboard[i][j] == Q) return false;
        }
        return true;
    }
    
    private List<String> toList(char[][] chessboard) {
        List<String> res = new ArrayList<>();
        for (char[] chars : chessboard) {
            res.add(String.copyValueOf(chars));
        }
        return res;
    }
}
