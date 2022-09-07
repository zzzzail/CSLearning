package com.java.dsa.algorithm;

import java.util.Arrays;

public class Sort {
    
    public static void main(String[] args) {
        Sort sort = new Sort();
        int[] arr1 = {3, 4, 2, 1, 5, 9};
        sort.bubbleSort(arr1);
        // sort.selectionSort(arr1);
        // sort.insertionSort(arr1);
        System.out.println(Arrays.toString(arr1));
    }
    
    /**
     * 冒泡排序
     */
    public void bubbleSort(int[] arr) {
        for (int i = 0; i < arr.length; ++i) {
            for (int j = 0; j < i; ++j) {
                if (arr[j] > arr[j + 1]) {   // 相邻元素两两比较
                    swap(arr, j, j + 1);  // 交换元素
                }
            }
        }
    }
    
    /**
     * 选择排序
     * 每次都选择后面最小的一个与当前需要处理的数组的第一个元素交换
     */
    public void selectionSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; ++i) {
            int min = i;
            for (int j = i + 1; j < arr.length; ++j) {
                if (arr[j] < arr[min]) min = j;
            }
            if (min != i) swap(arr, min, i);
        }
    }
    
    /**
     * 插入排序
     */
    public void insertionSort(int[] arr) {
        for (int i = 1; i < arr.length; ++i) {
            int cur = arr[i], j = i - 1;
            while (j >= 0 && cur < arr[j]) {
                arr[j + 1] = arr[j];
                --j;
            }
            arr[j + 1] = cur;
        }
    }
    
    private void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }
    
}
