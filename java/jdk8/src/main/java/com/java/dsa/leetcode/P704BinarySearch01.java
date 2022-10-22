package com.java.dsa.leetcode;

/**
 * @author zhangxq
 * @link https://leetcode.cn/problems/binary-search/?envType=study-plan&id=suan-fa-ru-men&plan=algorithms&plan_progress=407ao9i
 * @since 2022/10/22
 */
public class P704BinarySearch01 {
    
    public static void main(String[] args) {
    
    }
    
    public int search(int[] nums, int target) {
        int l = 0, r = nums.length - 1, mid = (r - l) / 2 + l;
        while (l <= r) {
            mid = (r - l) / 2 + l;
            if (target < nums[mid]) {
                r = mid - 1;
            }
            else if ( target > nums[mid] ) {
                l = mid + 1;
            }
            else { // target == nums[mid]
                return mid;
            }
        }
        return -1;
    }
}
