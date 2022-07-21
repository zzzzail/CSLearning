package com.dsa.leetcode;

/**
 * @link https://leetcode-cn.com/problems/find-minimum-in-rotated-sorted-array/
 * 寻找旋转排序数组中的最小值
 */
public class P153FindMinimumInRotatedSortedArray1 {
    
    public static void main(String[] args) {
        int[] nums1 = new int[]{2, 1};
        System.out.println(findMin(nums1));
    }
    
    /**
     * 二分查找
     * 把数组分成两部分，其中一部分一定是有序的，另一部分一定是无序的。
     */
    public static int findMin(int[] nums) {
        int left = 0, right = nums.length - 1;
        while (left < right) {
            int mid = (left + right) >> 1;
            // 判断是否是最小值
            if (mid > 0 && nums[mid] < nums[mid - 1]) return nums[mid];
            // 右半部分有序(先判断右边有序是因为如果出现极端情况会先往左边走)
            if (nums[mid] < nums[right]) right = mid - 1;
            else left = mid + 1;
        }
        return nums[left];
    }
}
