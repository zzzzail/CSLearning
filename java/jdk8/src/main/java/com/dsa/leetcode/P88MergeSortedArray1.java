package com.dsa.leetcode;

import java.util.Arrays;

/**
 * https://leetcode-cn.com/problems/merge-sorted-array/
 * 合并两个有序数组
 */
public class P88MergeSortedArray1 {
    
    public static void main(String[] args) {
        int[] num11 = new int[]{2, 0};
        int[] num12 = new int[]{1};
        merge(num11, 1, num12, 1);
        System.out.println(Arrays.toString(num11));
    }
    
    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        if (nums1 == null || nums2 == null) return;
        if (m == 0 && n == 0) return;
        if (n == 0) return;
        int[] copy = new int[m + n];
        System.arraycopy(nums1, 0, copy, 0, m + n);
        int p1 = 0, p2 = 0;
        for (int i = 0; i < m + n; i++) {
            if (p1 >= m) {
                nums1[i] = nums2[p2];
                ++p2;
                continue;
            }
            if (p2 >= n) {
                nums1[i] = copy[p1];
                ++p1;
                continue;
            }
            if (copy[p1] <= nums2[p2]) {
                nums1[i] = copy[p1];
                ++p1;
            }
            else {
                nums1[i] = nums2[p2];
                ++p2;
            }
        }
    }
}
