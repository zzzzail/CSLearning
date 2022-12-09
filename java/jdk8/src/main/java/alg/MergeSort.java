package alg;

import java.util.Arrays;

public class MergeSort {
    
    public static void main(String[] args) {
        MergeSort sort = new MergeSort();
        int[] arr1 = {3, 4, 2, 1, 5, 9};
        sort.mergeSort(arr1, 0, arr1.length - 1);
        System.out.println(Arrays.toString(arr1));
    }
    
    /**
     * 归并排序
     */
    public void mergeSort(int[] arr, int left, int right) {
        if (right <= left) return;
        int mid = (left + right) >> 1; // (left + right) / 2
        mergeSort(arr, left, mid);
        mergeSort(arr, mid + 1, right);
        merge(arr, left, mid, right);
    }
    
    /**
     * 两个有序的数组合并为一个有序数组
     */
    private void merge(int[] arr, int left, int mid, int right) {
        int[] temp = new int[right - left + 1]; // 中间数组
        int i = left, j = mid + 1, k = 0;
        while (i <= mid && j <= right) {
            temp[k++] = arr[i] <= arr[j] ? arr[i++] : arr[j++];
        }
        while (i <= mid) temp[k++] = arr[i++];
        while (j <= right) temp[k++] = arr[j++];
        
        // 也可以用 System.arraycopy
        // System.arraycopy(temp, 0, arr, left, temp.length);
        for (int p = 0; p < temp.length; ++p) {
            arr[left + p] = temp[p];
        }
    }
    
}
