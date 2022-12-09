package alg.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class P15ThreeSum2 {
    
    public static void main(String[] args) {
        int[] nums = new int[]{-1, 0, 1, 2, -1, -4};
        List<List<Integer>> result1 = threeSum(nums);
        System.out.println(result1);
        
        int[] nums2 = new int[]{0, 0, 0};
        List<List<Integer>> result2 = threeSum(nums2);
        System.out.println(result2);
    }
    
    /**
     * 三数之和
     * 1. 先排序
     * 2. 遍历 nums 数组
     *    i 指向正在处理的
     *    l 指向 i 的下一个元素
     *    r 指向 数组的末尾
     *    nums[i] + nums[l] + nums[r] = 0 时是预期的结果，加入到 res 中即可。
     *    nums[i] + nums[l] + nums[r] < 0 说明数值小了，需要往上加，l 向右移动。
     *    nums[i] + nums[l] + nums[r] > 0 说明数值大了，需要往下减，r 向左移动。
     *    l 和 r 移动期间都过掉重复元素
     */
    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null|| nums.length < 3) return res;
        
        // 第一步先排序 O(nlogn)
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 2; i++) {
            if (nums[i] > 0) return res; // 三个正整数相加不可能为 0
            if (i > 0 && nums[i] == nums[i - 1]) continue; // 数重复直接过
            
            int l = i + 1, r = nums.length - 1;
            while (l < r) {
                int cur = nums[i] + nums[l] + nums[r];
                if (cur == 0) {
                    res.add(Arrays.asList(nums[i], nums[l], nums[r]));
                    while (l < r && nums[l] == nums[l + 1]) { l++; } // 过掉重复的元素
                    while (l < r && nums[r] == nums[r - 1]) { r--; }
                    l++;
                    r--;
                }
                else if (cur < 0) {
                    l++;
                }
                else {
                    r--;
                }
            }
        }
        return res;
    }
}
