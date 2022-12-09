package alg.leetcode;

import java.util.ArrayList;
import java.util.List;

public class P51NQueens3 {
    
    /**
     * 1. 暴力
     * 2. dfs + 回溯
     */
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> res = new ArrayList<>();
        if (n == 0) return res;
        char[][] chessboard = new char[n][n];
        // 初始化棋盘
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                chessboard[i][j] = '.';
        
        dfs(n, chessboard, 0, res);
        return res;
    }
    
    private void dfs(int n, char[][] chessboard, int row, List<List<String>> res) {
        if (row == n) {
            res.add(toStringList(chessboard));
            return;
        }
        for (int col = 0; col < n; ++col) {
            if (validate(n, chessboard, row, col)) {     // 剪枝
                chessboard[row][col] = 'Q';
                dfs(n, chessboard, row + 1, res);
                chessboard[row][col] = '.';              // 回溯
            }
        }
    }
    
    private boolean validate(int n, char[][] chessboard, int row, int col) {
        char Q = 'Q';
        for (int i = 0; i < row; i++)
            if (chessboard[i][col] == Q) return false;
        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; --i, --j)
            if (chessboard[i][j] == Q) return false;
        for (int i = row - 1, j = col + 1; i >= 0 && j < n; --i, ++j)
            if (chessboard[i][j] == Q) return false;
        return true;
    }
    
    private List<String> toStringList(char[][] chessboard) {
        List<String> res = new ArrayList<>();
        for (char[] chars : chessboard) {
            res.add(String.copyValueOf(chars));
        }
        return res;
    }
    
}
