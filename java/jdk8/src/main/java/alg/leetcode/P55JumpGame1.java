package alg.leetcode;

/**
 * @link https://leetcode-cn.com/problems/jump-game/
 * 跳跃游戏
 */
public class P55JumpGame1 {
    
    public static void main(String[] args) {
    
    }
    
    /**
     * 贪心算法
     */
    public boolean canJump(int[] nums) {
        int n = nums.length;
        int rightmost = 0;
        for (int i = 0; i < n; ++i) {
            if (i <= rightmost) {
                rightmost = Math.max(rightmost, i + nums[i]);
                if (rightmost >= n - 1) { // 如果能跳过去，则返回 true
                    return true;
                }
            }
        }
        return false;
    }
    
    /**
     * 从后往前贪心
     */
    public static boolean canJump2(int[] nums) {
        if (nums == null) return false;
        int endReachable = nums.length - 1;
        for (int i = nums.length - 1; i >= 0; --i) {
            if (nums[i] + i >= endReachable) {
                endReachable = i;
            }
        }
        return endReachable == 0;
    }
}

