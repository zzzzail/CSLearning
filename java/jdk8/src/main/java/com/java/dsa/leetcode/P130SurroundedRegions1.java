package com.java.dsa.leetcode;


import com.java.dsa.struct.UnionFind;

/**
 * @link https://leetcode-cn.com/problems/surrounded-regions/
 * 被围绕的区域
 */
public class P130SurroundedRegions1 {
    
    public static void main(String[] args) {
    
    }
    
    int[] dx = new int[]{0, 0, -1, 1};
    int[] dy = new int[]{-1, 1, 0, 0};
    
    /**
     * 使用 DFS
     */
    public void solve(char[][] board) {
        int n = board.length;
        if (n == 0) return;
        int m = board[0].length;
        for (int i = 0; i < n; ++i) {
            dfs(board, i, 0);
            dfs(board, i, m - 1);
        }
        for (int i = 1; i < m - 1; ++i) {
            dfs(board, 0, i);
            dfs(board, n - 1, i);
        }
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < m; ++j) {
                if (board[i][j] == '#') {
                    board[i][j] = 'O';
                }
                else if (board[i][j] == 'O') {
                    board[i][j] = 'X';
                }
            }
        }
    }
    
    private void dfs(char[][] board, int row, int col) {
        // 说明已经搜索过了.
        if (!inArea(board, row, col) || board[row][col] == 'X' || board[row][col] == '#') return;
        board[row][col] = '#';
        for (int i = 0; i < 4; ++i) {
            dfs(board, row + dx[i], col + dy[i]);
        }
    }
    
    private boolean inArea(char[][] board, int row, int col) {
        return 0 <= row && row < board.length && 0 <= col && col < board[0].length;
    }
    
    /**
     * 将二维坐标转化为一维坐标，便于并查集使用
     * ｘ 为二维数组的一维索引，ｙ 为二维数组的二维索引
     */
    private int flatternTowDim(int x, int y, int width) {
        return x * width + y;
    }
    
    /**
     * 使用并查集
     */
    public void solve2(char[][] board) {
        if (board.length == 0) return;
        int len = board.length;
        int width = board[0].length;
        int boardSize = len * width;
        UnionFind uf = new UnionFind(boardSize + 1);
        // 添加一个虚拟节点，所有位于边界的Ｏ节点均与该虚拟节点相连接
        int i, j;
        for (i = 0; i < board.length; i++) {
            for (j = 0; j < board[0].length; j++) {
                if ((i == 0 || i == board.length - 1 || j == 0 || j == board[0].length - 1) && board[i][j] == 'O')
                    uf.union(flatternTowDim(i, j, width), boardSize);
            }
        }
        
        //遍历搜索相邻的Ｏ，添加到并查集中
        for (i = 0; i < board.length; i++) {
            for (j = 0; j < board[0].length; j++) {
                if (board[i][j] == 'O') {
                    //将当前Ｏ点与其上下左右四个方向的Ｏ点相连接
                    if (i - 1 >= 0 && board[i - 1][j] == 'O')
                        uf.union(flatternTowDim(i - 1, j, width), flatternTowDim(i, j, width));
                    if (i + 1 < board.length && board[i + 1][j] == 'O')
                        uf.union(flatternTowDim(i + 1, j, width), flatternTowDim(i, j, width));
                    if (j - 1 >= 0 && board[i][j - 1] == 'O')
                        uf.union(flatternTowDim(i, j - 1, width), flatternTowDim(i, j, width));
                    if (j + 1 <= board[0].length && board[i][j] == 'O')
                        uf.union(flatternTowDim(i, j + 1, width), flatternTowDim(i, j, width));
                }
            }
        }
        
        // 将所有与边界节点不相连的＇Ｏ＇点替换为＇Ｘ＇
        for (i = 0; i < board.length; i++) {
            for (j = 0; j < board[0].length; j++) {
                if (board[i][j] == 'O' && !uf.isConnected(flatternTowDim(i, j, width), boardSize))
                    board[i][j] = 'X';
            }
        }
    }
    
}
