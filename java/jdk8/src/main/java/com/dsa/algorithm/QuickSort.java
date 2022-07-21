package com.dsa.algorithm;

import java.util.Arrays;

public class QuickSort {
    
    public static void main(String[] args) {
        QuickSort sort = new QuickSort();
        int[] arr1 = {3, 4, 2, 1, 5, 9};
        sort.quickSort(arr1, 0, arr1.length - 1);
        System.out.println(Arrays.toString(arr1));
    }
    
    /**
     * 快速排序
     */
    public void quickSort(int[] arr, int begin, int end) {
        if (end <= begin) return;
        // int pivot = partition(arr, begin, end);
        int pivot = partition(arr, begin, end);
        quickSort(arr, begin, pivot - 1);
        quickSort(arr, pivot + 1, end);
    }
    
    private int partition(int[] arr, int begin, int end) {
        // 选最后一个元素作为枢纽
        // counter 为计数器，记 < arr[pivot] 的下标
        int pivot = end, lt = begin;
        for (int i = begin; i < end; i++) {
            if (arr[i] < arr[pivot]) {       // 每当遇到小于枢纽的情况，将元素往前挪动
                swap(arr, i, lt);
                ++lt;
            }
        }
        // 最后 counter 刚好停在 >= 枢纽的第一个元素
        swap(arr, lt, pivot);
        return lt;
    }
    
    private void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }
}
