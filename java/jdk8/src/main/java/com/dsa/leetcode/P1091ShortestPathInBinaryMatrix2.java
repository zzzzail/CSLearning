package com.dsa.leetcode;

import java.util.Objects;
import java.util.PriorityQueue;

public class P1091ShortestPathInBinaryMatrix2 {
    
    int[] dx = {0, 0, -1, 1, -1, 1, -1, 1};
    int[] dy = {-1, 1, 0, 0, -1, -1, 1, 1};
    
    /**
     * 常用的距离计算公式
     * 1. 曼哈顿距离（Manhattan Distance）
     * 一般只能在四个方向上移动时用（右、左、上、下）
     * 2. 对角线距离（Diagonal Distance）：
     * 当我们只允许向八个方向移动时用（国际象棋中的王移动方式那种）
     * 3. 欧几里得距离（Euclidean Distance）：
     * 不受限制，允许向任何方向移动时。
     * 4. 切比雪夫距离（Chebyshev Distance）：
     * 可参考 LeetCode 1266
     * @link https://leetcode-cn.com/problems/minimum-time-visiting-all-points/solution/fang-wen-suo-you-dian-de-zui-xiao-shi-jian-by-le-2/
     */
    public int shortestPathBinaryMatrix(int[][] grid) {
        int n = grid.length;
        if (grid[0][0] == 1 || grid[n - 1][n - 1] == 1) return -1;
        if (n == 1) return 1;
        
        PriorityQueue<Node> queue = new PriorityQueue<>(10, (i, j) -> Double.compare(i.cost, j.cost));
        queue.add(new Node(0, 0, 0));
        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            int x = cur.x;
            int y = cur.y;
            if (x == n - 1 && y == n - 1) return pathLength(cur);
            grid[y][x] = 1;
            for (int i = 0; i < 8; i++) {
                int newX = x + dx[i];
                int newY = y + +dy[i];
                if (0 <= newX && newX < n && 0 <= newY && newY < n && grid[newY][newX] == 0) {
                    double cost = cur.cost + 1 + distance(newX, newY, x, y);
                    Node next = new Node(newX, newY, cost);
                    // 加快向前走的脚步。
                    // 因为之前加入的所有路径中第一个一定是最优解，若最优解往回走的话就不是最优解了。
                    if (queue.contains(next)) continue;
                    grid[newY][newX] = 1;
                    // 保存路径，方便后面打印路径。也可以不用记录路径
                    next.setParent(cur);
                    queue.add(next);
                }
            }
        }
        return -1;
    }
    
    // 启发式函数，计算两点间的距离
    public double distance(int x, int y, int targetX, int targetY) {
        return Math.sqrt(Math.pow(targetX - x, 2) + Math.pow(targetY - y, 2));
    }
    
    private int pathLength(Node node) {
        if (node == null) return 0;
        int pathLength = 1;
        while (node.getParent() != null) {
            node = node.getParent();
            pathLength++;
        }
        return pathLength;
    }
    
    
    static class Node {
        public int x, y;
        public double cost;
        public Node parent = null;
        
        public Node(int x, int y) {
            this(x, y, 0);
        }
        
        public Node(int x, int y, double cost) {
            this.x = x;
            this.y = y;
            this.cost = cost;
        }
        
        public Node getParent() {
            return parent;
        }
        
        public void setParent(Node parent) {
            this.parent = parent;
        }
        
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node node = (Node) o;
            return x == node.x && y == node.y;
        }
        
        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }
    
}
