package alg.leetcode;

import java.util.HashSet;
import java.util.Set;

public class P874WalkingRobotSimulation {
    
    public static void main(String[] args) {
        int res1 = robotSim(new int[]{6, -1, -1, 6}, new int[][]{});
        System.out.println(res1);
        
        int res2 = robotSim(new int[]{4, -1, 4, -2, 4}, new int[][]{{2, 4}});
        System.out.println(res2);
    }
    
    // 北、东、南、西
    static int[] dx = new int[]{0, 1, 0, -1};
    static int[] dy = new int[]{1, 0, -1, 0};
    
    /**
     * 错误的算法！
     */
    public static int robotSim(int[] commands, int[][] obstacles) {
        int x = 0, y = 0, maxx = 0, maxy = 0;
        int face = 0; // 开始面向北，右转 90 度则 (face + 1) % 4、左转 90 度 face - 1
        for (int i = 0; i < commands.length; i++) {
            if (commands[i] == -1) face = (face + 1) % 4; // 右转
            if (commands[i] == -2) face = (face == 0 ? 4 : face - 1); // 左转
            if (commands[i] > 0) { // 向当前面向的方向前进
                int obSteps = canMoveSteps(face, x, y, commands[i], obstacles);
                int canMoveSteps = Math.min(commands[i], obSteps);
                x += canMoveSteps * dx[face];
                y += canMoveSteps * dy[face];
                maxx = Math.max(maxx, Math.abs(x));
                maxy = Math.max(maxy, Math.abs(y));
            }
        }
        return (int) (Math.pow(maxx, 2) + Math.pow(maxy, 2));
    }
    
    // 若有障碍物的情况下，最多可以往前移动多少步
    private static int canMoveSteps(int face, int x, int y, int steps, int[][] obstacles) {
        int res = Integer.MAX_VALUE;
        if (obstacles.length == 0) return res; // 没有障碍物
        for (int[] obstacle : obstacles) {
            int obx = obstacle[0], oby = obstacle[1];
            if (face == 0 && x == obx && y < oby && oby <= y + steps) { // 面向北 y + 1
                res = Math.min(res, oby - y - 1);
            }
            if (face == 1 && y == oby && x < obx && obx <= x + steps) { // 面向东 x + 1
                res = Math.min(res, obx - x - 1);
            }
            if (face == 2 && x == obx && y > oby && oby >= y - steps) { // 面向南 y - 1
                res = Math.min(res, y - oby - 1);
            }
            if (face == 3 && y == oby && x > obx && obx >= x - steps) { // 面向西 x - 1
                res = Math.min(res, x - obx - 1);
            }
        }
        return res;
    }
    
    
    public static int robotSim2(int[] commands, int[][] obstacles) {
        // 北、东、南、西
        int[] dx = new int[]{0, 1, 0, -1};
        int[] dy = new int[]{1, 0, -1, 0};
        int x = 0, y = 0, di = 0;
        // 把障碍物坐标编码成一个数
        // Encode obstacles (x, y) as (x+30000) * (2^16) + (y+30000)
        Set<Long> obstacleSet = new HashSet();
        for (int[] obstacle : obstacles) {
            long ox = (long) obstacle[0] + 30000;
            long oy = (long) obstacle[1] + 30000;
            obstacleSet.add((ox << 16) + oy);
        }
        
        int ans = 0;
        for (int cmd : commands) {
            if (cmd == -2) di = (di + 3) % 4; // 左转
            else if (cmd == -1) di = (di + 1) % 4; // 右转
            else {
                for (int k = 0; k < cmd; ++k) {
                    int nx = x + dx[di];
                    int ny = y + dy[di];
                    long code = (((long) nx + 30000) << 16) + ((long) ny + 30000);
                    if (!obstacleSet.contains(code)) {
                        x = nx;
                        y = ny;
                        ans = Math.max(ans, x * x + y * y);
                    }
                }
            }
        }
        return ans;
    }
}
