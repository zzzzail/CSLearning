package com.dsa.algorithm;

public class QuickSort2Ways {
    
    public void quickSort(int[] arr, int begin, int end) {
        if (end <= begin) return;
        // int pivot = partition(arr, begin, end);
        int pivot = partition(arr, begin, end);
        quickSort(arr, begin, pivot - 1);
        quickSort(arr, pivot + 1, end);
    }
    
    /**
     * 双路 partition
     * 返回 p，使得 arr[l...p-1] < arr[p]; arr[p+1...r] > arr[p]
     */
    private int partition(int[] arr, int begin, int end) {
        // 随机在 arr[l...r] 的范围中, 选择一个数值作为标定点 pivot
        swap(arr, begin, (int) (Math.random() * (end - begin + 1)) + begin);
        // 将 begin 位置的元素作为枢纽
        int pivotVal = arr[begin], i = begin + 1, j = end;
        while (true) {
            // 下面两个判断参考: http://coding.imooc.com/learn/questiondetail/4920.html
            // 从左侧开始找到第一个 > pivot 的元素
            while (i <= end && arr[i] < pivotVal) ++i;
            // 从右侧开始往前找到第一个 < pivot 的元素
            while (j >= begin + 1 && arr[j] > pivotVal) --j;
            if (i > j) break; // i 超过 j 才是结束
            swap(arr, i, j);
            ++i;
            --j;
        }
        swap(arr, begin, j); // j 指向最后一个 < pivot 的元素
        return j;
    }
    
    private void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }
}
