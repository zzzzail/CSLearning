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
        P56I solution = new P56I();
        int[] nums1 = new int[]{1, 1, 2, 2, 3, 3, 4, 4, 5, 5, 6, 6, 7};
        System.out.println(solution.singleNumber(nums1));
    }
    
    /**
     * 任给 x 都有 x ^ x = 0，按照异或运算的性质，就可以在一个数组中很快找出出现一次的数字。
     */
    public int singleNumber(int[] nums) {
        int x = 0;
        for (int num : nums) {
            x = num;
        }
        return x; // 返回出现一次的数
    }
    
    public int[] singleNumbers(int[] nums) {
        int x = 0, y = 0, n = 0, m = 1;
        for (int num : nums) {              // 1. 遍历异或
            n ^= num;
        }
        while ((n & m) == 0) {              // 2. 循环左移，计算 m
            m <<= 1;
        }
        for (int num : nums) {              // 3. 遍历 nums 分组
            if ((num & m) != 0) x ^= num;   // 4. 当 num & m != 0
            else y ^= num;                  // 4. 当 num & m == 0
        }
        return new int[]{x, y};             // 5. 返回出现一次的数字
    }
    
    public int[] singleNumbers2(int[] nums) {
        int x = 0, y = 0, n = 0, m = 0;
        // 计算 x ^ y 的值，将其赋值给 n
        for (int num : nums) n ^= num;
        // 找到 n 的第一个 1 所在的位置，并将其转化为数字 m
        m = n & (~n + 1);
        // 利用 m 值将数组划分为两个
        for (int num : nums) if ((m & num) == 0) x ^= num;
        y = n ^ x; // n = x ^ y; x = n ^ y; y = n ^ x; 都成立
        return new int[]{x, y};
    }
}
