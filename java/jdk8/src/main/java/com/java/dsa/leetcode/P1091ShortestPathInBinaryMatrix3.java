package com.java.dsa.leetcode;

import java.util.PriorityQueue;

public class P1091ShortestPathInBinaryMatrix3 {
    
    int n = 0;
    int[] dx = {0, 0, -1, 1, -1, 1, -1, 1};
    int[] dy = {-1, 1, 0, 0, -1, -1, 1, 1};
    
    /**
     * 1. 曼哈顿距离（Manhattan Distance）
     * 一般只能在四个方向上移动时用（右、左、上、下）
     * 2. 对角线距离（Diagonal Distance）：
     * 当我们只允许向八个方向移动时用（国际象棋中的王移动方式那种）
     * 3. 欧几里得距离（Euclidean Distance）：
     * 不受限制，允许向任何方向移动时。
     * 4. 切比雪夫距离（Chebyshev Distance）：
     * 可参考 LeetCode 1266
     *
     * @link https://leetcode-cn.com/problems/minimum-time-visiting-all-points/solution/fang-wen-suo-you-dian-de-zui-xiao-shi-jian-by-le-2/
     */
    public int shortestPathBinaryMatrix(int[][] grid) {
        this.n = grid.length;
        if (grid[0][0] != 0 || grid[n - 1][n - 1] != 0) return -1;
        if (n == 1) return 1;
        
        PriorityQueue<Node> queue = new PriorityQueue<>((n1, n2) -> n1.heuristicVal - n2.heuristicVal);
        queue.offer(new Node(0, 0, distance(1, 0, 0, n - 1, n - 1)));
        grid[0][0] = 1;
        while (!queue.isEmpty()) {
            Node curr = queue.poll();
            int x = curr.x, y = curr.y;
            // 到达目的地
            if (x == n - 1 && y == n - 1) return grid[x][y];
            
            for (int i = 0; i < 8; ++i) {
                int newX = x + dx[i];
                int newY = y + dy[i];
                if (newX < 0 || newX > n - 1 || newY < 0 || newY > n - 1) {
                    continue;
                }
                // 注意判断 grid[newX][newY] > grid[x][y] + 1
                if (grid[newX][newY] == 0 || grid[newX][newY] > grid[x][y] + 1) {
                    queue.offer(new Node(newX, newY, distance(grid[newX][newY], newX, newY, n - 1, n - 1)));
                    grid[newX][newY] = grid[x][y] + 1;
                }
            }
        }
        return -1;
    }
    
    // 距离计算方法
    public int distance(int step, int x, int y, int targetX, int targetY) {
        return step + Math.max(targetX - x, targetY - y);
    }
    
    public class Node {
        int x, y;
        int heuristicVal;
        
        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
        
        public Node(int x, int y, int heuristicVal) {
            this.x = x;
            this.y = y;
            this.heuristicVal = heuristicVal;
        }
        
        public int getHeuristicVal() {
            return heuristicVal;
        }
        
        public void setHeuristicVal(int heuristicVal) {
            this.heuristicVal = heuristicVal;
        }
    }
    
}
