package alg.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 四数之和
 *
 * @author zhangxq
 * @link https://leetcode.cn/problems/4sum/
 * @since 2022/12/11
 */
public class P18FourSum {
    
    public static void main(String[] args) {
        System.out.println((long) (1000000000) + 1000000000 + 1000000000 + 1000000000);
    }
    
    public List<List<Integer>> fourSum(int[] nums, int target) {
        // 先排序
        Arrays.sort(nums);
    
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            // 若遇到重复数字则跳过
            if (i > 0 && nums[i - 1] == nums[i]) continue;
            for (int j = i + 1; j < nums.length; j++) {
                if (j > i + 1 && nums[j - 1] == nums[j]) continue;
                for (int k = j + 1, u = nums.length - 1; k < u; k++) {
                    if (k > j + 1 && nums[k- 1] == nums[k]) continue;
                    // 这里要注意加法溢出的问题
                    while (u - 1 > k && (long) (nums[i]) + nums[j] + nums[k] + nums[u - 1] >= target) u--;
                    if ((long) (nums[i]) + nums[j] + nums[k] + nums[u] == target) {
                        res.add(Arrays.asList(nums[i], nums[j], nums[k], nums[u]));
                    }
                }
            }
        }
        
        return res;
    }
    
}
