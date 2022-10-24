package com.java.dsa.leetcode;

/**
 * @author zhangxq
 * @since 2022/10/24
 */
public class P4MedianOfTwoSortedArrays02 {
    
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int tot = nums1.length + nums2.length;
        if (tot % 2 == 0) {
            int l = findKth(nums1, 0, nums2, 0, tot / 2);
            int r = findKth(nums1, 0, nums2, 0, tot / 2 + 1);
            return (l + r) / 2.0;
        }
        else return findKth(nums1, 0, nums2, 0, tot / 2  + 1);
    }
    
    private int findKth(int[] nums1, int i, int[] nums2, int j, int k) {
        // 默认第一个数组中的值比较少，若不是就交换一下
        if (nums1.length - i > nums2.length - j) return findKth(nums2, j, nums1, i, k);
        // 处理边界条件
        if (k == 1) {
            // 第一个数组是空的
            if (nums1.length == i) return nums2[j];
            else return Math.min(nums1[i], nums2[j]);
        }
        // 如果第一个数组是空的
        if (nums1.length == i) return nums2[j + k - 1];
        
        // si 有可能越界
        int si = Math.min(nums1.length, i + k / 2), sj = j + k - k / 2;
        if (nums1[si - 1] > nums2[sj - 1])
            return findKth(nums1, i, nums2, sj, k - (sj - j));
        else // (nums1[si - 1] >= nums2[sj - 1])
            return findKth(nums1, si, nums2, j, k - (si - i));
    }
}
