package com.java.dsa.leetcode;

/**
 * @link https://leetcode-cn.com/problems/number-of-islands/
 * 岛屿数量
 */
public class P200NumberOfIslands1 {
    
    public static void main(String[] args) {
        char[][] grid1 = new char[][]{
                {'1', '1', '1', '1', '0'},
                {'1', '1', '0', '1', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '0', '0', '0'}
        };
        System.out.println(numIslands(grid1));
    }
    
    /**
     * @link https://leetcode-cn.com/problems/number-of-islands/solution/dao-yu-lei-wen-ti-de-tong-yong-jie-fa-dfs-bian-li-/
     * todo： 一会回来做
     */
    public static int numIslands(char[][] grid) {
        int count = 0;
        for (int row = 0; row < grid.length; ++row) {
            for (int col = 0; col < grid[0].length; ++col) {
                if (grid[row][col] == '1') {
                    area(grid, row, col);
                    ++count;
                }
            }
        }
        return count;
    }
    
    // 求某个区域的面积
    private static void area(char[][] grid, int row, int col) {
        if (!inArea(grid, row, col)) return;
        if (grid[row][col] != '1') return;
        grid[row][col] = '2'; // 标记该陆地已被访问
        area(grid, row - 1, col);
        area(grid, row + 1, col);
        area(grid, row, col - 1);
        area(grid, row, col + 1);
    }
    
    // 判断是否在区域内
    private static boolean inArea(char[][] grid, int row, int col) {
        return 0 <= row && row < grid.length &&
                0 <= col && col < grid[0].length;
    }
    
}
