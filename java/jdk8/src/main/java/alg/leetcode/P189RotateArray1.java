package alg.leetcode;

import java.util.Arrays;

/**
 * @link https://leetcode-cn.com/problems/rotate-array/
 * 轮转数组
 */
public class P189RotateArray1 {
    
    public static void main(String[] args) {
        int[] nums1 = new int[]{1, 2};
        rotate3(nums1, 3);
        System.out.println(Arrays.toString(nums1));
    }
    
    /**
     * 向后冒泡法
     * 时间复杂度 O(nk) 就离谱
     * 空间复杂度 O(1)
     */
    public static void rotate(int[] nums, int k) {
        if (k <= 0) return;
        if (nums == null || nums.length == 0) return;
        
        for (; k > 0; k--) {
            int bubble = nums[0];
            for (int i = 1; i < nums.length; i++) {
                int t = nums[i];
                nums[i] = bubble;
                bubble = t;
            }
            nums[0] = bubble;
        }
    }
    
    /**
     * 每个元素一次就移动到对的位置
     * [ 1, 2, 3, 4, 5, 6, 7 ]
     * ↓
     * ----------
     * ↓
     * [ 1, 2, 3, 4, 5, 6, 7 ]
     */
    public static void rotate2(int[] nums, int k) {
        if (k <= 0) return;
        if (nums == null || nums.length < 2) return;
        int[] numsd = new int[Math.min(k, nums.length)];
        System.arraycopy(nums, 0, numsd, 0, Math.min(k, nums.length));
        
        for (int i = 0; i < Math.min(k, nums.length); i++) { // 移动 k 次，每次移动 k 步
            int moveNum = numsd[i];
            int moveTo = i;
            // 移动到 0 ~ k - 1 区间内就结束了
            do {
                moveTo = (moveTo + k) % nums.length;
                int t = nums[moveTo];
                nums[moveTo] = moveNum;
                moveNum = t;
            }
            while (moveTo >= k);
        }
    }
    
    /**
     * 反转数组法
     * 1. 将数组整个反转
     * 2. 将数组看成 0 ～ k - 1 和 k ～ n - 1 两块，后对这两块进行反转
     */
    public static void rotate3(int[] nums, int k) {
        int n = nums.length;
        k %= nums.length;
        reverse(nums, 0, n - 1); // 旋转 [0, n - 1]
        reverse(nums, 0, k - 1);
        reverse(nums, k, n - 1);
    }
    
    private static void reverse(int[] nums, int start, int end) {
        while (start < end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
        }
    }
}
