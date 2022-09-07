package com.java.dsa.leetcode;

/**
 * @link https://leetcode.cn/problems/reverse-pairs/
 * 翻转对
 */
public class P493ReversePairs1 {
    
    public static void main(String[] args) {
        P493ReversePairs1 solution = new P493ReversePairs1();
        int[] nums1 = {9, 8, 7, 6, 5, 4, 3, 2, 1};
        solution.reversePairs(nums1);
    }
    
    /**
     * 1. 暴力 两层循环
     * 2. merge sort
     */
    public int reversePairs(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        return mergeSort(nums, 0, nums.length - 1);
    }
    
    private int mergeSort(int[] arr, int left, int right) {
        if (left >= right) return 0;
        
        int mid = (left + right) >> 1;
        int count = mergeSort(arr, left, mid) + mergeSort(arr, mid + 1, right);
        
        // 这里的 arr[left...mid], arr[mid + 1...right] 是有序的
        // count 单独统计逆序对数量
        int i = left, j = mid + 1;
        while (i <= mid && j <= right) {
            if (arr[i] > 2L * arr[j]) {  // 如果前半部分第 i 个值是后面的翻转对
                count += mid - i + 1;    // i 到 mid 的所有数字都满足翻转对的要求，
                                         // 所以就不用 i 继续向下判断了
                j++;
            }
            else i++;
        }
        
        merge(arr, left, mid, right);
        return count;
    }
    
    private void merge(int[] arr, int left, int mid, int right) {
        int[] cache = new int[right - left + 1];
        
        int i = left, j = mid + 1, k = 0;
        while (i <= mid && j <= right) {
            cache[k++] = arr[i] <= arr[j] ? arr[i++] : arr[j++];
        }
        while (i <= mid) cache[k++] = arr[i++];
        while (j <= right) cache[k++] = arr[j++];
        System.arraycopy(cache, 0, arr, left, cache.length);
    }
    
}
