package alg.leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @link https://leetcode-cn.com/problems/valid-sudoku/
 * 有效的数独
 * 数字 1 - 9 每一行只能出现一次
 * 数字 1 - 9 每一列只能出现一次
 * 数字 1 - 9 每一个以实线分割的 3 X 3 宫内只能出现一次
 */
public class P36ValidSudoku {
    
    public static void main(String[] args) {
    
    }
    
    /**
     * 遍历的时候判断三个条件即可
     * 在第 i 个行中是否出现过
     * 在第 j 个列中是否出现过
     * 在第 (i / 3) * 3 + j / 3 个 box 中是否出现过
     */
    public boolean isValidSudoku(char[][] board) {
        Map<Integer, Set<Integer>> row = new HashMap<>();
        Map<Integer, Set<Integer>> col = new HashMap<>();
        Map<Integer, Set<Integer>> area = new HashMap<>();
        for (int i = 0; i < 9; i++) {
            row.put(i, new HashSet<>());
            col.put(i, new HashSet<>());
            area.put(i, new HashSet<>());
        }
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                char c = board[i][j];
                if (c == '.') continue;
                int x = c - '0';
                int idx = (i / 3) * 3 + j / 3;
                if (row.get(i).contains(x) || col.get(j).contains(x) || area.get(idx).contains(x)) {
                    return false;
                }
                row.get(i).add(x);
                col.get(j).add(x);
                area.get(idx).add(x);
            }
        }
        return true;
    }
    
    /**
     * 数组
     */
    public boolean isValidSudoku2(char[][] board) {
        boolean[][] row = new boolean[9][9];
        boolean[][] col = new boolean[9][9];
        boolean[][][] block = new boolean[3][3][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                char c = board[i][j];
                if (c == '.') continue;
                int u = c - '1';
                if (row[i][u] || col[j][u] || block[i / 3][j / 3][u]) return false;
                row[i][u] = col[j][u] = block[i / 3][j / 3][u] = true;
            }
        }
        return true;
    }
    
    /**
     * 位运算
     */
    public boolean isValidSudoku3(char[][] board) {
        int[] row = new int[10];
        int[] col = new int[10];
        int[] area = new int[10];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                char c = board[i][j];
                if (c == '.') continue;
                int u = c - '0';
                int idx = i / 3 * 3 + j / 3;
                if ((((row[i] >> u) & 1) == 1) || (((col[j] >> u) & 1) == 1) || (((area[idx] >> u) & 1) == 1)) {
                    return false;
                }
                row[i] |= (1 << u);
                col[j] |= (1 << u);
                area[idx] |= (1 << u);
            }
        }
        return true;
    }
    
}
