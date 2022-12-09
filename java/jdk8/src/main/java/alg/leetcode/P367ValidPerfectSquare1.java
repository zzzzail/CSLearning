package alg.leetcode;

/**
 * @link https://leetcode-cn.com/problems/valid-perfect-square/
 * 有效的完全平凡数
 */
public class P367ValidPerfectSquare1 {
    
    public static void main(String[] args) {
        boolean res1 = isPerfectSquare2(808201);
        System.out.println(res1);
    }
    
    /**
     * 使用牛顿迭代法快速找到平方根
     */
    public boolean isPerfectSquare(int num) {
        if (num <= 0) return false;
        long r = num;
        while (r * r > num) {
            r = (r + num / r) / 2;
        }
        return r * r == num;
    }
    
    /**
     * 使用二分查找法
     */
    public static boolean isPerfectSquare2(int num) {
        if (num <= 0) return false;
        if (num == 1) return true;
        int low = 1, high = num;
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (mid * mid > num) high = mid - 1;
            else low = mid + 1;
        }
        return high * high == num;
    }
}