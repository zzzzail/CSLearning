package com.dsa.leetcode;

/**
 * @link https://leetcode-cn.com/problems/max-area-of-island/
 * 岛屿的最大面积
 */
public class P695MaxAreaOfIsland {
    
    /**
     * DFS 解法
     * 矩阵的 DFS 和树的 DFS 有点不同
     * 任意 POS_ij 结点有上下左右四个分支，即 POS_(i-1)j、POS_(i+1)j、POS_i(j-1)、POS_i(j+1)
     * 前提是这些分支不要超过边界
     *
     * 题解：
     * @link https://leetcode-cn.com/problems/number-of-islands/solution/dao-yu-lei-wen-ti-de-tong-yong-jie-fa-dfs-bian-li-/
     */
    public static int maxAreaOfIsland(int[][] grid) {
        int res = 0;
        // 循环遍历所有的陆地结点，到底看看那块陆地面积最大
        for (int row = 0; row < grid.length; ++row) {
            for (int col = 0; col < grid[0].length; ++col) {
                if (grid[row][col] == 1) {
                    int area = area(grid, row, col);
                    res = Math.max(res, area);
                }
            }
        }
        return res;
    }
    
    private static int area(int[][] grid, int row, int col) {
        // 如果超出边界则直接返回
        if (!inArea(grid, row, col)) return 0;
        // 如果不是岛屿则直接返回 0
        if (grid[row][col] != 1) return 0;
        grid[row][col] = 2; // 如果是陆地，则修改为已访问
        return 1 +
                area(grid, row - 1, col) +
                area(grid, row + 1, col) +
                area(grid, row, col - 1) +
                area(grid, row, col + 1);
    }
    
    /**
     * 判断位置是否在区域内
     */
    private static boolean inArea(int[][] grid, int row, int col) {
        return 0 <= row && row < grid.length &&
                0 <= col && col < grid[0].length;
    }
}
