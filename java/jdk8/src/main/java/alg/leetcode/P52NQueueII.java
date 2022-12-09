package alg.leetcode;

/**
 * @link https://leetcode.cn/problems/n-queens-ii/
 * N 皇后 II
 */
public class P52NQueueII {
    
    public static void main(String[] args) {
        P52NQueueII solution = new P52NQueueII();
        int res1 = solution.totalNQueens(4);
        System.out.println(res1);
    }
    
    int count = 0;
    
    public int totalNQueens(int n) {
        char[][] chessboard = new char[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                chessboard[i][j] = '.';
            }
        }
        dfs(n, chessboard, 0);
        return count;
    }
    
    private void dfs(int n, char[][] chessboard, int row) {
        if (row == n) {
            ++count;
            return;
        }
        for (int col = 0; col < n; ++col) {
            if (validate(n, chessboard, row, col)) {
                chessboard[row][col] = 'Q';
                dfs(n, chessboard, row + 1);
                chessboard[row][col] = '.';
            }
        }
    }
    
    public boolean validate(int n, char[][] chessboard, int row, int col) {
        char Q = 'Q';
        // 检查列，仅判断 row 之前即可
        for (int i = 0; i < row; i++) {
            if (chessboard[i][col] == Q) return false;
        }
        // 检查 45 度角是否有皇后
        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
            if (chessboard[i][j] == Q) return false;
        }
        // 检查 135 度角是否有皇后
        for (int i = row - 1, j = col + 1; i >= 0 && j < n; i--, j++) {
            if (chessboard[i][j] == Q) return false;
        }
        return true;
    }
    
    public int totalNQueens2(int n) {
        int[] rs = {0, 1, 0, 0, 2, 10, 4, 40, 92, 352, 724, 2680};
        return rs[n];
    }
    
}
