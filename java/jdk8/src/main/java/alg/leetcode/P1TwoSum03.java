package alg.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhangxq
 * @since 2022/10/24
 */
public class P1TwoSum03 {
    
    public static void main(String[] args) {
    
    }
    
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i ++) {
            int t = target - nums[i];
            if (map.get(t) != null) return new int[]{map.get(t), i};
            map.put(nums[i], i);
        }
        return null;
    }
}
