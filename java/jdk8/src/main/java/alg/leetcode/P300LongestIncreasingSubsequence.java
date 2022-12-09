package alg.leetcode;

import java.util.Arrays;

/**
 * @link https://leetcode.cn/problems/longest-increasing-subsequence/
 * 最长递增子序列
 */
public class P300LongestIncreasingSubsequence {
    
    public static void main(String[] args) {
        P300LongestIncreasingSubsequence solution = new P300LongestIncreasingSubsequence();
        int[] nums1 = {10, 9, 2, 5, 3, 7, 101, 18};
        int res1 = solution.lengthOfLIS2(nums1);
        System.out.println(res1);
        
        int[] nums2 = {7, 7, 7, 7, 7, 7, 7};
        int res2 = solution.lengthOfLIS2(nums2);
        System.out.println(res2);
        
        int[] nums3 = {1};
        int res3 = solution.lengthOfLIS2(nums3);
        System.out.println(res3);
        
        int[] nums4 = {0, 1, 0, 3, 2, 3};
        int res4 = solution.lengthOfLIS3(nums4);
        System.out.println(res4);
    }
    
    /**
     * 动态规划
     * 子问题： nums[0...i] 的最长递增子序列为：dp[0...i - 1] 中凡是小于 nums[i] 的最长递增子序列的最大者。
     * DP 数组定义： dp[i] 表示 nums[0...i] 的最长递增子序列的长度
     * DP 方程：
     * |         dp[i] = max(dp[i], if nums[i] > nums[0...i - 1]: dp[0...i - 1] + 1)
     * DP 初始化： 每一个 nums[i] 作为单独的一个最长递增子序列，长度都为 1
     * 结果： dp 数组中的最大值
     */
    public int lengthOfLIS(int[] nums) {
        int n = nums.length;
        if (n <= 1) return n;
        
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        int maxVal = 0;
        for (int i = 1; i < n; ++i) {
            for (int j = 0; j < i; ++j) {
                if (nums[j] < nums[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            maxVal = Math.max(maxVal, dp[i]);
        }
        return maxVal;
    }
    
    
    /**
     * 这个代码神了 1ms ！
     * 一个递增子数组就是对 nums 依次处理第 i 个数字，拼接上第 i 个数字后，记下最长的记录即可
     * 例如： nums =           [ 10, 9, 2,   5, 3, 7, 101, 18 ]
     * |     处理 10  ： lis = [ 10, 0, 0,   0, 0, 0,   0,  0 ]   （10 是递增子数组的第一个元素）
     * |     处理 9   ： lis = [  9, 0, 0,   0, 0, 0,   0,  0 ]   （这里要回退一格，因为 9 没有 10 大，所以9 成为了最长子数组的第一个元素）
     * |     处理 2   ： lis = [  2, 0, 0,   0, 0, 0,   0,  0 ]   （9 > 2， 所以 2 成为最长子数组的第一个元素）
     * |     处理 5   ： lis = [  2, 5, 0,   0, 0, 0,   0,  0 ]   （2 < 5， 拼接上 5 可以形成有两个元素的最长子数组）
     * |     处理 3   ： lis = [  2, 3, 0,   0, 0, 0,   0,  0 ]   （这里的 3 导致了一次回退）
     * |     处理 7   ： lis = [  2, 3, 7,   0, 0, 0,   0,  0 ]
     * |     处理 101 ： lis = [  2, 3, 7, 101, 0, 0,   0,  0 ]
     * |     处理 18  ： lis = [  2, 3, 7,  18, 0, 0,   0,  0 ]
     */
    public int lengthOfLIS2(int[] nums) {
        // 保存递增最长子序列的数组
        int[] lis = new int[nums.length];
        lis[0] = nums[0];  // 初始化
        int lislen = 1;    // 记录最长子序列数组的长度
        int maxlen = 1;
        for (int i = 1; i < nums.length; ++i) {
            int num = nums[i];
            // j 用于向前遍历 lis 数组
            int j;
            for (j = maxlen - 1; j >= 0; --j) { // 注意这里 j 不是 = lislen - 1！
                // 如果遇到 lis 数组中的某个值 < 当前处理的数 num （说明拼接上 num 可以形成递增子序列）
                if (lis[j] < num) break;
            }
            lis[j + 1] = num;   // j 刚好停在 < num 的位置上，下一个位置拼接上 num 即可
            lislen = j + 2;     // j + 1 是最后一个元素的位置，数组的长度是 j + 2
            maxlen = Math.max(maxlen, lislen);
        }
        return maxlen;
    }
    
    // 上面代码的简化版
    public int lengthOfLIS3(int[] nums) {
        int[] lis = new int[nums.length];
        lis[0] = nums[0];
        int maxlen = 1;
        for (int i = 1; i < nums.length; ++i) {
            int num = nums[i];
            
            int j;
            for (j = maxlen - 1; j >= 0; --j) {
                if (lis[j] < num) break;
            }
            lis[j + 1] = num;
            maxlen = Math.max(maxlen, j + 2);
        }
        return maxlen;
    }
    
    // 上一版的神奇版本
    public int lengthOfLIS4(int[] nums) {
        int res = -1;
        int[] dp = new int[nums.length];
        for (int num : nums) {
            int j;
            for (j = res; j >= 0; j--)
                if (dp[j] < num) break;
            dp[j + 1] = num;
            res = Math.max(res, j + 1);
        }
        return res + 1;
    }
    
}
