package com.java.dsa.leetcode.jzof;

import java.util.Arrays;

/**
 * 最小的 k 个数
 *
 * @author zail
 * @link https://leetcode.cn/problems/zui-xiao-de-kge-shu-lcof/
 * @date 2022/7/21
 */
public class P40 {
    
    public static void main(String[] args) {
    
    }
    
    public int[] getLeastNumbers(int[] arr, int k) {
        int[] res = new int[k];
        Arrays.sort(arr);
        for (int i = 0; i < k; i++) {
            res[i] = arr[i];
        }
        return res;
    }
    
    public int[] getLeastNumbers2(int[] arr, int k) {
        int[] res = new int[k];
        quickSort(arr, 0, arr.length - 1);
        for (int i = 0; i < k; i++) {
            res[i] = arr[i];
        }
        return res;
    }
    
    void quickSort(int[] arr, int left, int right) {
        if (left >= right) return;
        int pivotVal = arr[left];
        int lt = left;       // arr[left ... lt] < pivotVal
                             // arr(lt ... i) == pivotVal
        int i = left + 1;    // arr[i ... gt) 需要处理的数据
        int gt = right + 1;  // arr[gt ... right] > pivotVal
        while (i < gt) {
            if (arr[i] < pivotVal) {
                swap(arr, i, ++lt);
                i++;
            }
            else if (arr[i] > pivotVal) {
                swap(arr, i, --gt);
            }
            else { // arr[i] == pivotVal
                i++;
            }
        }
        swap(arr, left, lt);
        quickSort(arr, left, lt - 1);
        quickSort(arr, gt, right);
    }
    
    private void swap(int[] arr, int i1, int i2) {
        if (i1 == i2) return;
        int t = arr[i1];
        arr[i1] = arr[i2];
        arr[i2] = t;
    }
}
