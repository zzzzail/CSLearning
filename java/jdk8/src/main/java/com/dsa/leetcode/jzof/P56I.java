package com.dsa.leetcode.jzof;

/**
 * 数组中数字出现的次数
 *
 * @author zail
 * @link https://leetcode.cn/problems/shu-zu-zhong-shu-zi-chu-xian-de-ci-shu-lcof/
 * @date 2022/7/21
 */
public class P56I {
    
    public static void main(String[] args) {
    
    }
    
    public int[] singleNumbers(int[] nums) {
        int x = 0, y = 0, n = 0, m = 1;
        for (int num : nums)                // 1. 遍历异或
            n ^= num;
        while ((n & m) == 0)                // 2. 循环左移，计算 m
            m <<= 1;
        for (int num : nums) {              // 3. 遍历 nums 分组
            if ((num & m) != 0) x ^= num;   // 4. 当 num & m != 0
            else y ^= num;                  // 4. 当 num & m == 0
        }
        return new int[]{x, y};             // 5. 返回出现一次的数字
    }
}
