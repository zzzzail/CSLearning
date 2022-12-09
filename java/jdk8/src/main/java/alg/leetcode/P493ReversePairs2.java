package alg.leetcode;

public class P493ReversePairs2 {
    
    public static void main(String[] args) {
        P493ReversePairs2 solution = new P493ReversePairs2();
        int[] nums1 = {9, 8, 7, 6, 5, 4, 3, 2, 1};
        solution.reversePairs(nums1);
    }
    
    public int reversePairs(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        return mergeSort(nums, 0, nums.length - 1);
    }
    
    private int mergeSort(int[] nums, int l, int r) {
        if (l >= r) return 0;
        
        int mid = l + (r - l) / 2;
        int count = mergeSort(nums, l, mid) + mergeSort(nums, mid + 1, r);
        
        int[] cache = new int[r - l + 1];
        // i 处理逆序对， j 处理
        int i = l, t = l, c = 0;
        for (int j = mid + 1; j <= r; j++, c++) {
            while (i <= mid && nums[i] <= 2 * (long) nums[j]) i++;
            while (t <= mid && nums[t] < nums[j]) cache[c++] = nums[t++];
            cache[c] = nums[j];
            count += mid - i + 1;
        }
        while (t <= mid) cache[c++] = nums[t++];
        System.arraycopy(cache, 0, nums, l, cache.length);
        
        return count;
    }
    
}
