package alg.leetcode;

import java.util.Arrays;

/**
 * @link https://leetcode.cn/problems/race-car/
 * 赛车
 * A 加速指令 position += speed; speed *= 2 （以 2 的指数级别加速）
 * R 倒车指令 如果 speed 是正数 speed = -1 否则 speed = 1
 * 往前跑的时候，第一次倒车是真实的倒车，第二次倒车是倒向前行驶。
 */
public class P818RaceCar {
    
    public static void main(String[] args) {
    
    }
    
    /**
     * 动态规划
     * DP 子问题：
     * DP 数组定义：
     * DP 方程：
     * 初始化：
     * 结果：
     */
    public int racecar(int target) {
        return 0;
    }
    
    public int racecar2(int target) {
        // 处理边界
        if (target <= 0) return 0;
        
        int[] dp = new int[target + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        for (int i = 1; i <= target; ++i) {
            // 先向前走 forward 步      2^forward - 1 < 2i (走到 < 2i 的位置，就是 i 之前的位置)
            for (int forward = 1; (1 << forward) - 1 < 2 * i; ++forward) {
                // 向前走了 forwardDistance
                int forwardDistance = (1 << forward) - 1;
                // 对应第一种情况，走了 forward 步直接到达 i
                if (forwardDistance == i) {
                    dp[i] = forward;
                }
                else if (forwardDistance > i) { // 对应第二种情况，越过了i
                    // 往回还需要走的距离就是 2^n-1 - target
                    // +1 是因为回头需要一个R指令
                    dp[i] = Math.min(dp[i], forward + 1 + dp[forwardDistance - i]);
                }
                else { // 对应第三种情况，没有越过 i 即 forwardDistance < i
                    // 先回头走 backward 步
                    for (int backward = 0; backward < forward; ++backward) {
                        int backwardDistance = (1 << backward) - 1;
                        // forward + 1 意思是往前走掉头
                        // backward + 1 意思是掉头之后，回退了 backward 步，还需要一个掉头往正的方向走
                        // dp[i - forwardDistance + backwardDistance]
                        // i - forwardDistance 是 forwardDistance 还没到目标位置 i 的距离
                        // 还没到目标位置 i 的距离 + backwardDistance 意思是回退几步就加几步
                        // 回退的每一步都要尝试找到最短的指令序列
                        // 第一个 +1 是还没到达 i，先回头，使用一个 R
                        // 第二个 +1 是回头走了 backwardDistance，再使用 R 回头走向 i
                        dp[i] = Math.min(dp[i], forward + 1 + backward + 1 + dp[i - forwardDistance + backwardDistance]);
                    }
                }
            }
        }
        return dp[target];
    }
    
}
