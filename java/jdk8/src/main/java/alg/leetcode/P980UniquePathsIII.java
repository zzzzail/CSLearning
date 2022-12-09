package alg.leetcode;

/**
 * @link https://leetcode-cn.com/problems/unique-paths-iii/
 * 不同路径 III
 */
public class P980UniquePathsIII {
    
    public static void main(String[] args) {
        /*
        [1, 0, 0,  0]
        [0, 0, 0,  0]
        [0, 0, 2, -1]
        解释：我们有以下两条路径：
        1. (0,0),(0,1),(0,2),(0,3),(1,3),(1,2),(1,1),(1,0),(2,0),(2,1),(2,2)
        2. (0,0),(1,0),(2,0),(2,1),(1,1),(0,1),(0,2),(0,3),(1,3),(1,2),(2,2)
        
        [1, 0, 0, 0]
        [0, 0, 0, 0]
        [0, 0, 0, 2]
        输出：4
        解释：我们有以下四条路径：
        1. (0,0),(0,1),(0,2),(0,3),(1,3),(1,2),(1,1),(1,0),(2,0),(2,1),(2,2),(2,3)
        2. (0,0),(0,1),(1,1),(1,0),(2,0),(2,1),(2,2),(1,2),(0,2),(0,3),(1,3),(2,3)
        3. (0,0),(1,0),(2,0),(2,1),(2,2),(1,2),(1,1),(0,1),(0,2),(0,3),(1,3),(2,3)
        4. (0,0),(1,0),(2,0),(2,1),(1,1),(0,1),(0,2),(0,3),(1,3),(1,2),(2,2),(2,3)
         */
    }
    
    int res = 0, empty = 1, sx, sy, ex, ey;
    /**
     * 注意：
     * 1. 2 表示目的方格，若遇到这个方格则可直接返回结果
     * 2. 每一个无障碍方格都必须通过一次，但是一条路径中不能重复通过同一个方格。
     */
    public int uniquePathsIII(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j] == 0) empty++;
                else if (grid[i][j] == 1) {
                    sx = i;
                    sy = j;
                }
            }
        }
        dfs(grid, sx, sy);
        return res;
    }
    
    public void dfs(int[][] grid, int x, int y) {
        if (x < 0 || x >= grid.length || y < 0 || y >= grid[0].length || grid[x][y] < 0)
            return;
        if (grid[x][y] == 2) {
            if (empty == 0) res++;
            return;
        }
        grid[x][y] = -2;
        empty--;
        dfs(grid, x + 1, y);
        dfs(grid, x - 1, y);
        dfs(grid, x, y + 1);
        dfs(grid, x, y - 1);
        grid[x][y] = 0;
        empty++;
    }
    
}
