package alg.leetcode;

import java.util.PriorityQueue;
import java.util.Queue;

public class P1091ShortestPathInBinaryMatrix4 {
    
    final int[][] dirs = new int[][]{{0, 1}, {1, 0}, {1, 1}, {0, -1}, {-1, 0}, {-1, -1}, {-1, 1}, {1, -1}};
    
    public int shortestPathBinaryMatrix(int[][] grid) {
        int n = grid.length;
        if (grid[0][0] != 0 || grid[n - 1][n - 1] != 0) return -1;
        if (n == 1) return 1;
        
        // Heuristic based priority Queue-Choose closest to the target
        Queue<int[]> queue = new PriorityQueue<>((a, b) -> {
            if (a[2] != b[2]) return a[2] - b[2];
            else {
                // 曼哈顿距离
                int manhattenDistanceA = (n - 1 - a[0]) + (n - 1 - a[1]);
                int manhattenDistanceB = (n - 1 - b[0]) + (n - 1 - b[1]);
                return manhattenDistanceA - manhattenDistanceB;
            }
        });
        // The above queue stores a,b,c where c is the least distance from source
        queue.offer(new int[]{0, 0, 1});
        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            if (grid[current[0]][current[1]] != 0) {
                continue;
            }
            grid[current[0]][current[1]] = -1; // Mark the least distant node from source as visited
            for (int[] dir : dirs) {
                int newX = current[0] + dir[0];
                int newY = current[1] + dir[1];
                if (newX >= 0 && newX < n && newY >= 0 && newY < n && grid[newX][newY] == 0) {
                    queue.offer(new int[]{newX, newY, current[2] + 1});
                }
                // Special check to get the end
                if (newX == n - 1 && newY == n - 1) {
                    return current[2] + 1;
                }
            }
        }
        return -1; // No path found
    }
    
}
