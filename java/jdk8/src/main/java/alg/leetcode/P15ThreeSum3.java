package alg.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 三数之和
 */
public class P15ThreeSum3 {
    
    public static void main(String[] args) {
        int[] nums = new int[]{-1, 0, 1, 2, -1, -4};
        List<List<Integer>> result1 = threeSum(nums);
        System.out.println(result1);
        
        int[] nums2 = new int[]{0, 0, 0};
        List<List<Integer>> result2 = threeSum(nums2);
        System.out.println(result2);
        
        // [ -4, -1, -1, 0, 1, 2 ]
        int[] nums3 = new int[]{-1, 0, 1, 2, -1, -4};
        List<List<Integer>> result3 = threeSum(nums3);
        System.out.println(result3);
    }
    
    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length < 3) return res;
        Arrays.sort(nums); // 先排序
        for (int i = 0; i < nums.length - 2; ++i) {
            if (nums[i] > 0) break; // 三个正整数相加不可能为 0
            if (i > 0 && nums[i] == nums[i + 1]) continue; // 过掉重复元素
            
            int l = i + 1, r = nums.length - 1;
            while (l < r) {
                int cur = nums[i] + nums[l] + nums[r];
                if (cur == 0) {
                    res.add(Arrays.asList(nums[i], nums[l], nums[r]));
                    while (l < r && nums[l] == nums[l + 1]) ++l;
                    while (l < r && nums[r] == nums[r - 1]) --r;
                    ++l;
                    --r;
                }
                else if (cur < 0) ++l;
                else --r;
            }
        }
        return res;
    }
}
