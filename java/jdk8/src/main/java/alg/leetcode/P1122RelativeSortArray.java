package alg.leetcode;

/**
 * @link https://leetcode.cn/problems/relative-sort-array/
 * 数组的相对顺序
 */
public class P1122RelativeSortArray {
    
    public static void main(String[] args) {
    
    }
    
    /**
     * 用计数排序
     * 按照 arr2 排序
     */
    public int[] relativeSortArray(int[] arr1, int[] arr2) {
        // 用于计数排序的数组
        int[] count = new int[1001];
        for (int num1 : arr1) {
            count[num1]++;
        }
        // 将 arr2 中的元素都按照顺序处理完成
        int i = 0;
        for (int num2 : arr2) {
            while (count[num2] > 0) {
                arr1[i++] = num2;
                count[num2]--;
            }
        }
        // 之后在处理 arr1 中剩下的数字
        for (int j = 0; j < count.length; j++) {
            while (count[j] > 0) {
                arr1[i++] = j;
                count[j]--;
            }
        }
        return arr1;
    }
    
}
