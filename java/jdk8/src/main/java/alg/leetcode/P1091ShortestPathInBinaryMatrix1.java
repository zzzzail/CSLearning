package alg.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @link https://leetcode-cn.com/problems/shortest-path-in-binary-matrix/
 * 二进制矩阵中的最短路径
 */
public class P1091ShortestPathInBinaryMatrix1 {
    
    public static void main(String[] args) {
        int[][] grid1 = {{0, 1}, {1, 0}};
        P1091ShortestPathInBinaryMatrix1 solution = new P1091ShortestPathInBinaryMatrix1();
        int res1 = solution.shortestPathBinaryMatrix(grid1);
        System.out.println(res1);
        
        int[][] grid2 = {{0, 0, 0}, {1, 1, 0}, {1, 1, 0}};
        int res2 = solution.shortestPathBinaryMatrix(grid2);
        System.out.println(res2);
        
        int[][] grid3 = {{0, 0, 0}, {1, 1, 0}, {1, 1, 1}};
        int res3 = solution.shortestPathBinaryMatrix(grid3);
        System.out.println(res3);
    }
    
    static class Node {
        int x;
        int y;
        int step;
        
        public Node(int x, int y, int step) {
            this.x = x;
            this.y = y;
            this.step = step;
        }
    }
    
    int[] dx = {0, 0, -1, 1, -1, 1, -1, 1};
    int[] dy = {-1, 1, 0, 0, -1, -1, 1, 1};
    
    /**
     * 1. DP
     * 2. BFS
     * 3. A*
     */
    public int shortestPathBinaryMatrix(int[][] grid) {
        int n = grid.length;
        if (grid[0][0] == 1 || grid[n - 1][n - 1] == 1) return -1;
        else if (n <= 2) return n;
        
        Node node = new Node(0, 0, 2);
        Deque<Node> queue = new ArrayDeque<>();
        queue.addLast(node);
        while (!queue.isEmpty()) {
            Node cur = queue.removeFirst();
            int x = cur.x, y = cur.y, step = cur.step;
            for (int i = 0; i < 8; ++i) {
                int newX = x + dx[i];
                int newY = y + dy[i];
                if (0 <= newX && newX < n && 0 <= newY && newY < n && grid[newX][newY] == 0) {
                    if (newX == n - 1 && newY == n - 1) return step; // 找到终点
                    queue.addLast(new Node(newX, newY, step + 1));
                    grid[newX][newY] = 1; // 标记已遍历过，避免重复
                }
            }
        }
        return -1;
    }
    
}
