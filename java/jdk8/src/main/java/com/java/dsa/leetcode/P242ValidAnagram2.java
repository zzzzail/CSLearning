package com.java.dsa.leetcode;

import java.util.Arrays;

public class P242ValidAnagram2 {
    
    public static void main(String[] args) {
        P242ValidAnagram2 solution = new P242ValidAnagram2();
        String s1 = "anagram";
        String s2 = "nagaram";
        boolean res1 = solution.isAnagram(s1, s2);
        System.out.println(res1);
    }
    
    /**
     * 2 路快排 12 ms 左右
     */
    public boolean isAnagram(String s, String t) {
        char[] ca1 = s.toCharArray();
        char[] ca2 = t.toCharArray();
        quickSort(ca1);
        quickSort(ca2);
        return Arrays.equals(ca1, ca2);
    }
    
    private void quickSort(char[] arr) {
        if (arr == null || arr.length == 0) return;
        quickSort(arr, 0, arr.length - 1);
    }
    
    private void quickSort(char[] arr, int begin, int end) {
        if (begin >= end) return;
        int pivot = partition(arr, begin, end);
        quickSort(arr, begin, pivot - 1);
        quickSort(arr, pivot + 1, end);
    }
    
    private int partition(char[] arr, int begin, int end) {
        swap(arr, begin, (int) (Math.random() * (end - begin + 1)) + begin);
        char pivotVal = arr[begin];
        int i = begin + 1, j = end;
        while (true) {
            while (i <= end && arr[i] < pivotVal)        ++i;
            while (j >= begin + 1 && arr[j] > pivotVal)  --j;
            if (i > j) break;
            swap(arr, i, j);
            ++i;
            --j;
        }
        swap(arr, begin, j);
        return j;
    }
    
    private void swap(char[] arr, int a, int b) {
        char t = arr[a];
        arr[a] = arr[b];
        arr[b] = t;
    }
}
