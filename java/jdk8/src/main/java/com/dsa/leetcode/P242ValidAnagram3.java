package com.dsa.leetcode;

import java.util.Arrays;

public class P242ValidAnagram3 {
    
    public static void main(String[] args) {
        P242ValidAnagram3 solution = new P242ValidAnagram3();
        String s1 = "anagram";
        String s2 = "nagaram";
        boolean res1 = solution.isAnagram(s1, s2);
        System.out.println(res1);
    }
    
    /**
     * 利用排序解决
     * 3 路快排 6ms 左右，比 2 路快排快一倍
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
    
    // 3 路快排
    private void quickSort(char[] arr, int begin, int end) {
        if (begin >= end) return;
        
        swap(arr, begin, (int) (Math.random() * (end - begin + 1)) + begin);
        char pivotVal = arr[begin];
        int i = begin + 1, lt = begin, gt = end + 1;
        while (i < gt) {
            if (arr[i] < pivotVal) {
                swap(arr, i, ++lt);
                ++i;
            }
            else if (arr[i] > pivotVal) {
                swap(arr, i, --gt);
            }
            else { // arr[i] == pivotVal
                ++i;
            }
        }
        swap(arr, begin, lt);
        quickSort(arr, begin, lt - 1);
        quickSort(arr, gt, end);
    }
    
    private void swap(char[] arr, int a, int b) {
        char t = arr[a];
        arr[a] = arr[b];
        arr[b] = t;
    }
}
