package alg.leetcode;

import java.util.ArrayList;
import java.util.List;

public class P37SudokuSolver2 {
    
    boolean[][] row = new boolean[9][9];
    boolean[][] col = new boolean[9][9];
    boolean[][][] block = new boolean[3][3][9];
    // 记录需要放置数字的位置
    List<int[]> places = new ArrayList<>();
    boolean done = false;
    
    public void solveSudoku(char[][] board) {
        int n = 9;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                char c = board[i][j];
                if (c == '.') places.add(new int[]{i, j});
                else {
                    int u = c - '1';
                    row[i][u] = col[j][u] = block[i / 3][j / 3][u] = true;
                }
            }
        }
        dfs(board, 0);
    }
    
    private void dfs(char[][] board, int pos) {
        if (pos == places.size()) {
            done = true;
            return;
        }
        
        int[] place = places.get(pos);
        int i = place[0], j = place[1];
        for (int digits = 0; digits < 9 && !done; digits++) {
            if (!row[i][digits] && !col[j][digits] && !block[i / 3][j / 3][digits]) {
                row[i][digits] = col[j][digits] = block[i / 3][j / 3][digits] = true;
                board[i][j] = (char) (digits + '1'); // place
                dfs(board, pos + 1);
                row[i][digits] = col[j][digits] = block[i / 3][j / 3][digits] = false;
            }
        }
    }
}
