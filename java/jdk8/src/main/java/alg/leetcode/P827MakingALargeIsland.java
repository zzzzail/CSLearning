package alg.leetcode;

/**
 * @link https://leetcode-cn.com/problems/making-a-large-island/
 */
public class P827MakingALargeIsland {
    
    public static void main(String[] args) {
        int[][] grid1 = new int[][]{
                {0,0,0,0,0,0,0},
                {0,1,1,1,1,0,0},
                {0,1,0,0,1,0,0},
                {1,0,1,0,1,0,0},
                {0,1,0,0,1,0,0},
                {0,1,0,0,1,0,0},
                {0,1,1,1,1,0,0},
        };
        System.out.println(largestIsland(grid1));
    }
    
    public static int largestIsland(int[][] grid) {
        int maxArea = 0;
        for (int row = 0; row < grid.length; ++row) {
            for (int col = 0; col < grid[0].length; ++col) {
                if (grid[row][col] == 0) {
                    // todo： 复制网格的方式肯定超时
                    int[][] newGrid = new int[grid.length][grid[0].length];
                    for (int i = 0; i < grid.length; ++i) {
                        System.arraycopy(grid[i], 0, newGrid[i], 0, grid[i].length);
                    }
                    newGrid[row][col] = 1;
                    int area = area(newGrid, row, col);
                    maxArea = Math.max(maxArea, area);
                }
            }
        }
        // 如果网格中没有 0 返回所有的面积
        if (maxArea == 0) {
            maxArea = area(grid, 0, 0);
        }
        return maxArea;
    }
    
    // 求出面积
    private static int area(int[][] grid, int row, int col) {
        if (!inArea(grid, row, col)) return 0;
        if (grid[row][col] != 1) return 0;
        grid[row][col] = 2;
        // 求面积
        return 1 +
                area(grid, row - 1, col) +
                area(grid, row + 1, col) +
                area(grid, row, col - 1) +
                area(grid, row, col + 1);
    }
    
    private static boolean inArea(int[][] grid, int row, int col) {
        return 0 <= row && row < grid.length &&
                0 <= col && col < grid[0].length;
    }
}
