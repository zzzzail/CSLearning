package alg.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @link https://leetcode-cn.com/problems/n-queens/
 * N 皇后
 */
public class P51NQueens1 {
    
    public static void main(String[] args) {
        P51NQueens1 solution = new P51NQueens1();
        List<List<String>> res1 = solution.solveNQueens(8);
        System.out.println(res1);
    }
    
    /**
     * 回溯解法
     * 最多只有 n 个放置结果（第一行的 n 个元素分别放置 Queeen）
     */
    public List<List<String>> solveNQueens(int n) {
        // 初始化棋盘
        char[][] chessboard = new char[n][n];
        for (char[] c : chessboard) {
            Arrays.fill(c, '.');
        }
        List<List<String>> res = new ArrayList<>();
        // 回溯
        backtrack(n, 0, chessboard, res);
        return res;
    }
    
    /**
     * 回溯算法
     *
     * @param n          大小
     * @param row        行，也是深度
     * @param chessboard 当前棋盘
     */
    public void backtrack(int n, int row, char[][] chessboard, List<List<String>> res) {
        // 回溯算法结束条件
        if (row == n) {
            res.add(array2List(chessboard));
            return;
        }
        for (int col = 0; col < n; ++col) {                   // 列循环
            if (isValid(n, chessboard, row, col)) {           // 如果放置的位置可行
                chessboard[row][col] = 'Q';                   // 放入皇后
                backtrack(n, row + 1, chessboard, res);  // 向下一行递归
                chessboard[row][col] = '.';                   // 回溯
            }
        }
    }
    
    /**
     * 检查 row, col 指向的位置是否可以放置皇后
     * 为什么没有在同行进行检查呢？
     * 因为在单层搜索的过程中，每一层递归，只会选 for 循环（也就是同一行）里的一个元素，所以不用去重了。
     */
    public boolean isValid(int n, char[][] chessboard, int row, int col) {
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
    
    public List<String> array2List(char[][] chessboard) {
        List<String> list = new ArrayList<>();
        for (char[] c : chessboard) {
            list.add(String.copyValueOf(c));
        }
        return list;
    }
    
    /**
     */
    public List<List<String>> solveNQueens2(int n) {
        return null;
    }
}
