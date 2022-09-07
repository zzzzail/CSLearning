package com.java.dsa.leetcode.jzof;

/**
 * 旋转数组的最小数字
 *
 * @author zail
 * @link https://leetcode.cn/problems/xuan-zhuan-shu-zu-de-zui-xiao-shu-zi-lcof/
 * @date 2022/7/17
 */
public class P11 {
    
    public static void main(String[] args) {
        P11 solution = new P11();
        
        int[] numbers1 = {3, 4, 5, 1, 2};
        System.out.println(solution.minArray(numbers1));
    }
    
    /**
     * 使用二分查找找到最小的那个值即可
     */
    public int minArray(int[] numbers) {
        int low = 0, high = numbers.length - 1, mid = 0;
        while (low < high) {
            mid = (low + high) >> 1;
            // mid ~ high 是无序的
            if (numbers[mid] > numbers[high]) low = mid + 1;
            // mid ~ high 是有序的
            else if (numbers[mid] < numbers[high]) high = mid;
            else high--;
        }
        return numbers[low];
    }
}
