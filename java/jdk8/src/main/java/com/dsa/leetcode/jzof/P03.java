package com.dsa.leetcode.jzof;

import java.util.HashMap;
import java.util.Map;

/**
 * 数组中重复的数字
 *
 * @author zail
 * @link https://leetcode.cn/problems/shu-zu-zhong-zhong-fu-de-shu-zi-lcof/
 * @date 2022/7/17
 */
public class P03 {
    
    public static void main(String[] args) {
    
    }
    
    public int findRepeatNumber(int[] nums) {
        if (nums == null) return -1;
        int n = nums.length;
        if (n == 0) return -1;
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        for (Integer key : map.keySet()) {
            if (map.get(key) > 1) {
                return key;
            }
        }
        return -1;
    }
}
