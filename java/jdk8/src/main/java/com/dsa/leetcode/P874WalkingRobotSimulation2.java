package com.dsa.leetcode;

import java.util.HashSet;
import java.util.Set;

public class P874WalkingRobotSimulation2 {
    
    public static void main(String[] args) {
    
    }
    
    public static int robotSim(int[] commands, int[][] obstacles) {
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
    
    public static int robotSim2(int[] commands, int[][] obstacles) {
        // 使用 set 储存障碍物的坐标，往前走的时候检查是否遇到障碍物
        // 使用字符串的方式保存障碍物的坐标会慢
        Set<String> set = new HashSet<>();
        for (int[] obs : obstacles) {
            set.add(obs[0] + " " + obs[1]);
        }
        int[] dx = new int[]{0, 1, 0, -1};
        int[] dy = new int[]{1, 0, -1, 0};
        int d = 0, x = 0, y = 0, result = 0;
        for (int c : commands) {
            if (c == -2) d = (d + 3) % 4;        // 左转
            else if (c == -1) d = (d + 1) % 4;   // 右转
            else {
                while (c-- > 0 && !set.contains((x + dx[d]) + " " + (y + dy[d]))) {
                    x += dx[d];
                    y += dy[d];
                }
            }
            result = Math.max(result, x * x + y * y);
        }
        return result;
    }
}
