package com.dsa.algorithm;

public class HeapSort {
    
    /**
     * 堆排序
     */
    public void heapSort(int[] arr) {
        if (arr.length == 0) return;
        int n = arr.length;
        for (int i = n / 2 - 1; i >= 0; --i) {
            heapify(arr, n, i);
        }
        for (int i = n - 1; i >= 0; --i) {
            int temp = arr[0]; arr[0] = arr[i]; arr[i] = temp;
            heapify(arr, i, 0);
        }
    }
    
    public void heapify(int[] arr, int n, int i) {
        int left = 2 * i + 1, right = 2 * i + 2;
        int largest = i;
        if (left < n && arr[left] > arr[largest]) {
            largest = left;
        }
        if (right < n && arr[right] > arr[largest]) {
            largest = right;
        }
        if (largest != i) {
            swap(arr, i, largest);
            heapify(arr, n, largest);
        }
    }
    
    private void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }
}
