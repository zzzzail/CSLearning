package alg;

import java.util.Arrays;

public class QuickSort3Ways {
    
    public static void main(String[] args) {
        QuickSort3Ways sort = new QuickSort3Ways();
        int[] arr1 = {3, 4, 2, 1, 5, 9, 8, 7, 6};
        sort.quickSort(arr1);
        System.out.println(Arrays.toString(arr1));
    }
    
    public void quickSort(int[] arr) {
        int n = arr.length;
        quickSort(arr, 0, n - 1);
    }
    
    // 递归使用快速排序,对arr[l...r]的范围进行排序
    private void quickSort(int[] arr, int left, int right) {
        if (left >= right) return;
        
        // 随机在 arr[l...r] 的范围中, 选择一个数值作为标定点 pivot
        swap(arr, left, (int) (Math.random() * (right - left + 1)) + left);
        int pivot = arr[left];
        int lt = left;         // arr[l + 1...lt] < pivot
        int i = left + 1;      // arr[lt + 1...i) == pivot
        int gt = right + 1;    // arr[gt...r] > pivot
        while (i < gt) {       // 当前处理的下标不能超过大于 pivot 的区域
            if (arr[i] < pivot) {
                swap(arr, i, ++lt);
                ++i;
            }
            else if (arr[i] > pivot) {
                swap(arr, i, --gt);
            }
            else { // arr[i] == pivot
                ++i;
            }
        }
        swap(arr, left, lt);
        quickSort(arr, left, lt - 1);
        quickSort(arr, gt, right);
    }
    
    private static void swap(int[] arr, int i, int j) {
        int t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }
    
}
