package alg.leetcode;

import java.util.Arrays;

/**
 * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
 *
 * [ 1, 2, 0, 4, 0, 9 ] => [ 1, 2, 4, 9, 0, 0 ]
 * 注意：需要保持非零元素的相对顺序。
 *
 * 1. 暴力解法：
 *    声明一个相同长度的数组，并将其填充为全 0，遍历 nums，将其中非 0 元素顺序加入到
 *    新声明的数组中。
 *    返回新声明的数组即可。
 *    时间复杂度 O(n)
 *    空间复杂度 O(n)
 * 2. 双指针法：
 *    将数组分为三块，无 0 块、0 和未处理块
 *    [ 1, 2, 0, 0, 4, 9 ]
 *    无 0 块｜0｜ 未处理块
 *
 *    i 指针指向当前未处理块的第 1 个元素，j 指向无 0 块后的第一个 0 值。
 *    判断 i 指针指向的值是否为 0，若不为 0 则与 j 指针指向的值交换。
 *    若为 0 则继续向后移动。
 *    直至遍历完成。
 *    时间复杂度 O(n)
 *    空间复杂度 O(1)
 * 3. https://leetcode.com/problems/move-zeroes/discuss/172432/THE-EASIEST-but-UNUSUAL-snowball-JAVA-solution-BEATS-100-(O(n))-%2B-clear-explanation
 *    snowball
 *    The pictorial "snowball" explanation is catchy and easy to generalize 👍
 *    这个滚雪球的画面解释又容易理解又好记
 */
public class P283MoveZeroes1 {
    
    public static void main(String[] args) {
        int[] nums = {1, 2, 0, 0, 4, 9};
        moveZeroes2(nums);
        System.out.println(Arrays.toString(nums));
        
        int[] nums2 = {0, 0, 1};
        moveZeroes2(nums2);
        System.out.println(Arrays.toString(nums2));
    }
    
    public static void moveZeroes(int[] nums) {
        int[] newNums = new int[nums.length];
        int j = 0;
        for (int num : nums) {
            if (num != 0) {
                newNums[j] = num;
                j++;
            }
        }
        System.arraycopy(newNums, 0, nums, 0, nums.length);
    }
    
    public static void moveZeroes2(int[] nums) {
        // 先让 j 找到第一个 0
        int j = 0;
        for ( ; j < nums.length; j ++ ) if ( nums[j] == 0 ) break;
        for (int i = j + 1; i < nums.length; i ++) {
            if ( nums[i] != 0 ) {
                swap(nums, i, j ++);
            }
        }
    }
    
    /**
     * 快慢指针法
     */
    public static void moveZeroes3(int[] nums) {
        if (nums == null || nums.length == 0) return;
        
        for ( int fast = 0, slow = 0; fast < nums.length; fast ++ ) {
            if ( nums[fast] != 0 ) {
                swap(nums, fast, slow ++);
            }
        }
    }
    
    /**
     * 优化快慢指针法，减少交换值的次数。
     * 因为慢指针每次 ++ 的时候都指向一个 0 值，且 fast 指针循环完成后就说明 slow 指针及 slow 指针之后的值都是0。
     * 所以可以优化代码
     */
    public static void moveZeroes4(int[] nums) {
        int slow = 0;
        for ( int fast = 0; fast < nums.length; fast ++ ) {
            if (nums[fast] != 0) {
                if (fast != slow)
                    nums[slow] = nums[fast];
                slow ++;
            }
        }
        // 最后将 slow 和 slow 之后的值都赋值为 0
        for ( ; slow < nums.length; slow ++ ) {
            nums[slow] = 0;
        }
    }
    
    /**
     * FROM 覃超
     * [ 1, 2, 0, 0, 4, 9 ]
     *         j     i
     */
    public static void moveZeroes5(int[] nums) {
        for (int i = 0, j = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                if (i != j) {
                    nums[j] = nums[i];
                    nums[i] = 0;
                }
                j ++;
            }
        }
    }
    
    private static void swap(int[] nums, int i1, int i2) {
        if (i1 == i2) return;
        int t = nums[i1];
        nums[i1] = nums[i2];
        nums[i2] = t;
    }
}
