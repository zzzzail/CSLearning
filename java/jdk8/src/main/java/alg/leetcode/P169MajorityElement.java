package alg.leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @link https://leetcode-cn.com/problems/majority-element/
 * 多数元素
 */
public class P169MajorityElement {
    
    public static void main(String[] args) {
    
    }
    
    /**
     * 暴力解法
     * 使用 Map 记录元素出现的次数。记录完成之后遍历 Map 找到出现次数大于 ⌊ n/2 ⌋ 的元素。
     */
    public int majorityElement(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            if (map.containsKey(num))
                map.put(num, map.get(num) + 1);
            else map.put(num, 1);
        }
        int half = nums.length / 2;
        for (Integer i : map.keySet())
            if (map.get(i) > half) return i;
        return 0;
    }
    
    /**
     * 排序法，排序完下标为 ⌊ n/2 ⌋ 一定是众数
     */
    public static int majorityElement2(int[] nums) {
        Arrays.sort(nums);
        return nums[nums.length / 2];
    }
    
    /**
     * 使用分治 + 回溯
     */
    public static int majorityElement3(int[] nums) {
        return majorityElementRec(nums, 0, nums.length - 1);
    }
    
    public static int majorityElementRec(int[] nums, int lo, int hi) {
        if (lo == hi) return nums[lo];
        // 分治
        int mid = (lo + hi) >> 1;
        int left = majorityElementRec(nums, lo, mid);
        int right = majorityElementRec(nums, mid + 1, hi);
        // if the two halves agree on the majority element, return it.
        if (left == right) {
            return left;
        }
        // otherwise, count each element and return the "winner".
        int leftCount = countInRange(nums, left, lo, hi);
        int rightCount = countInRange(nums, right, lo, hi);
        return leftCount > rightCount ? left : right;
    }
    
    private static int countInRange(int[] nums, int num, int lo, int hi) {
        int count = 0;
        for (int i = lo; i <= hi; i++) {
            if (nums[i] == num) {
                count++;
            }
        }
        return count;
    }
}
