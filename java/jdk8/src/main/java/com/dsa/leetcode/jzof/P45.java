package com.dsa.leetcode.jzof;

import java.util.Arrays;

/**
 * 把数组排程最小的数
 *
 * @author zail
 * @link https://leetcode.cn/problems/ba-shu-zu-pai-cheng-zui-xiao-de-shu-lcof/
 * @date 2022/7/20
 */
public class P45 {
    
    public static void main(String[] args) {
    
    }
    
    public String minNumber(int[] nums) {
        String[] strs = new String[nums.length];
        for (int i = 0; i < nums.length; i++) strs[i] = String.valueOf(nums[i]);
        Arrays.sort(strs, (x, y) -> (x + y).compareTo(y + x));
        StringBuilder res = new StringBuilder();
        for (String s : strs) res.append(s);
        return res.toString();
    }
}
