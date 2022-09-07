package com.java.dsa.leetcode;

/**
 * @link https://leetcode-cn.com/problems/search-in-rotated-sorted-array/
 * 搜索旋转排序数组
 * 从旋转排序数组中搜索值 target
 */
public class P33SearchInRotatedSortedArray1 {
    
    public static void main(String[] args) {
        int[] nums1 = new int[]{4, 5, 6, 7, 0, 1, 2};
        int res1 = search(nums1, 0);
        System.out.println(res1);
    }
    
    /**
     * 搜索不到返回 -1
     */
    public static int search(int[] nums, int target) {
        // 有序就往下找，无序就摆烂
        int low = 0, high = nums.length - 1;
        while (low <= high) {
            int mid = (low + high) >> 1;
            if (nums[low] > nums[high]) { // 开始摆烂
            
            }
            if (nums[mid] == target) return mid;
            else if (nums[mid] < target) high = mid - 1;
            else low = mid + 1;
        }
        return -1;
    }
    
    /**
     * 二分查找
     * 1. 首先明白，旋转数组后，从中间划分，一定有一边是有序的。
     * 2. 由于一定有一边是有序的，所以根据有序的两个边界值来判断目标值在有序一边还是无序一边
     * 3. 这题找目标值，遇到目标值即返回
     * 4. 注意：由于有序的一边的边界值可能等于目标值，所以判断目标值是否在有序的那边时应该加个等号
     * (在二分查找某个具体值得时候如果把握不好边界值，可以再每次查找前判断下边界值，也就是while循
     * 环里面的两个if注释)
     */
    public static int search2(int[] nums, int target) {
        int len = nums.length;
        if (len == 0) return -1;
        int left = 0, right = len - 1;
        while (left <= right) {
            // if(nums[left] == target) return left;
            // if(nums[right] == target) return right;
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) return mid;
            // 左边有序
            // todo 为什么先判断左边有序不可行？
            if (nums[left] < nums[mid]) {
                // 判断目标值是否在左边
                if (nums[left] <= target && target < nums[mid]) right = mid - 1;
                else left = mid + 1;
            }
            else { // 否则就是右边有序
                if (nums[mid] < target && target <= nums[right]) left = mid + 1;
                else right = mid - 1;
            }
        }
        return -1;
    }
    
    public static int search3(int[] nums, int target) {
        int len = nums.length;
        if (len == 0) return -1;
        int left = 0, right = len - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) return mid;
            if (nums[mid] < nums[right]) {
                if (target > nums[mid] && target <= nums[right]) left = mid + 1;
                else right = mid - 1;
            }
            else {
                if (target >= nums[left] && target < nums[mid]) right = mid - 1;
                else left = mid + 1;
            }
        }
        return -1;
    }
}
