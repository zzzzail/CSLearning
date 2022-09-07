package com.java.dsa.leetcode;

/**
 * @link https://leetcode-cn.com/problems/island-perimeter/
 * 岛屿的周长
 */
public class P463IslandPerimeter1 {
    
    public static void main(String[] args) {
    
    }
    
    public int islandPerimeter(int[][] grid) {
        for (int row = 0; row < grid.length; ++row) {
            for (int col = 0; col < grid[0].length; ++col) {
                if (grid[row][col] == 1) {
                    return perimeter(grid, row, col);
                }
            }
        }
        return 0;
    }
    
    private int perimeter(int[][] grid, int row, int col) {
        if (!inArea(grid, row, col)) return 1;
        if (grid[row][col] == 0) return 1;
        if (grid[row][col] != 1) return 0;
        grid[row][col] = 2;
        return perimeter(grid, row - 1, col) + perimeter(grid, row + 1, col) + perimeter(grid, row, col - 1) + perimeter(grid, row, col + 1);
    }
    
    private boolean inArea(int[][] grid, int row, int col) {
        return 0 <= row && row < grid.length
                && 0 <= col && col < grid[0].length;
    }
    
}
