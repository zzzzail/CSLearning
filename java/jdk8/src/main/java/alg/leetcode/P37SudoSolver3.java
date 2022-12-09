package alg.leetcode;

import java.util.ArrayList;
import java.util.List;

public class P37SudoSolver3 {
    int n = 9;
    boolean[][] row = new boolean[n][n];
    boolean[][] col = new boolean[n][n];
    boolean[][][] block = new boolean[3][3][n];
    List<int[]> blank = new ArrayList<>();
    boolean done = false;
    public void solveSudoku(char[][] board) {
        // 初始化
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                char c = board[i][j];
                if (c == '.') blank.add(new int[]{i, j});
                else {
                    int u = c - '0' - 1;
                    row[i][u] = col[j][u] = block[i / 3][j / 3][u] = true;
                }
            }
        }
        dfs(board, 0);
    }
    
    // dfs 往 pos 位置上填数字
    private void dfs(char[][] board, int pos) {
        if (pos == blank.size()) {
            done = true; // 标记结束
            return;
        }
        int[] b = blank.get(pos);
        int i = b[0], j = b[1];
        for (char c = '1'; c <= '9' && !done; ++c) {
            int u = c - '0' - 1;
            if (!row[i][u] && !col[j][u] && !block[i/3][j/3][u]) {
                row[i][u] = col[j][u] = block[i / 3][j / 3][u] = true;
                board[i][j] = c;
                dfs(board, pos + 1);
                row[i][u] = col[j][u] = block[i / 3][j / 3][u] = false;
            }
        }
    }
}
