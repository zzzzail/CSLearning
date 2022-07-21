package com.dsa.leetcode.jzof;

/**
 * 在排序数组中查找数字 I
 * 统计数字在数组中出现的次数
 *
 * @author zail
 * @link https://leetcode.cn/problems/zai-pai-xu-shu-zu-zhong-cha-zhao-shu-zi-lcof/
 * @date 2022/7/17
 */
public class P53 {
    
    public static void main(String[] args) {
        P53 solution = new P53();
        
        int[] nums1 = new int[]{5, 7, 7, 8, 8, 10};
        int res1 = solution.search(nums1, 8);
        System.out.println(res1);
    
        int[] nums2 = new int[]{5,7,7,8,8,10};
        int res2 = solution.search(nums2, 6);
        System.out.println(res2);
    }
    
    /**
     * nums 是一个有序数组
     * 1. 通过二分查找找到数字 target 所在的位置（第一个 target 所在的位置）
     * 2. 向后统计数字 target 出现的次数，直到遇到数字不是 target 的数，即可结束统计
     * 3. 返回统计后的结果
     */
    public int search(int[] nums, int target) {
        int position = binarySearch(nums, target);
        if (position == -1) return 0;
        
        int n = nums.length;
        int count = 1;
        for (int i = position + 1; i < n; i++) {
            if (nums[i] == target) count++;
            else break;
        }
        return count;
    }
    
    private int binarySearch(int[] nums, int target) {
        int low = 0, high = nums.length - 1, mid = 0;
        int res = -1;
        while (low <= high) {
            mid = (low + high) >> 1;
            // 找到了
            if (nums[mid] == target) {
                res = mid;
                break;
            }
            else if (target < nums[mid]) {
                high = mid - 1;
            }
            else { // if (target > nums[mid]) {
                low = mid + 1;
            }
        }
        if (res == -1 || res == 0) return res;
        // 向前查找到第一个 target
        for (int i = res; i >= 0; i--) {
            if (nums[i] == target) res = i;
            else break;
        }
        return res;
    }
}
