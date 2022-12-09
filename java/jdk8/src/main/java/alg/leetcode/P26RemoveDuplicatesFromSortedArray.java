package alg.leetcode;

import java.util.Arrays;

/**
 * https://leetcode-cn.com/problems/remove-duplicates-from-sorted-array/
 * 删除有序数组中的重复项
 */
public class P26RemoveDuplicatesFromSortedArray {
    
    public static void main(String[] args) {
        int[] nums = new int[]{1, 1, 2};
        int n1 = removeDuplicates(nums);
        System.out.println(Arrays.toString(nums));
        System.out.println(n1);
    }
    
    /**
     * 三指针
     * i 始终指向去重后数组的最后位置
     * d 处理重复数字的下标
     * 第一趟：
     * [ 1, 2, 2, 2 ]
     * i
     * d
     * 第二趟：
     * [ 1, 2, 2, 2 ]
     * i
     * d
     */
    public static int removeDuplicates(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int i = 0, d = 0;
        for (; d < nums.length; i++, d++) {
            // 找到不重复元素的最后一个
            while (d < nums.length - 1 && nums[d] == nums[d + 1]) {
                d++;
            }
            if (i != d) nums[i] = nums[d];
        }
        return i;
    }
    
    /**
     * 一个指针 i 进行数组遍历，另外一个指针 j 指向有效数组的最后一个位置。
     * 只有当 i 所指向的值和 j 不一致（不重复），才将 i 的值添加到 j 的下一位置。
     */
    public static int removeDuplicates2(int[] nums) {
        int n = nums.length;
        int j = 0;
        for (int i = 0; i < n; i++) {
            if (nums[i] != nums[j]) {
                nums[++j] = nums[i];
            }
        }
        return j + 1;
    }
    
    /**
     * count 记录重复元素的个数
     */
    public static int removeDuplicates3(int[] nums) {
        int count = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i - 1]) count++;
            else nums[i - count] = nums[i];
        }
        return nums.length - count;
    }
}
