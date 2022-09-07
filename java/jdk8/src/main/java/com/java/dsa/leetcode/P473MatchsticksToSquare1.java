package com.java.dsa.leetcode;

import java.util.Arrays;

/**
 * 火柴拼正方形
 *
 * @author zail
 * @link <a href="https://leetcode.cn/problems/matchsticks-to-square/">火柴拼正方形</a>
 * @date 2022/6/1
 */
public class P473MatchsticksToSquare1 {
    
    public static void main(String[] args) {
        P473MatchsticksToSquare1 solution = new P473MatchsticksToSquare1();
        int[] matchsticks1 = {1, 1, 2, 2, 2};
        boolean res1 = solution.makesquare2(matchsticks1);
        System.out.println(res1);
        
        int[] matchsticks2 = {3, 3, 3, 3, 4};
        boolean res2 = solution.makesquare(matchsticks2);
        System.out.println(res2);
        
        int[] matchsticks3 = {10, 6, 5, 5, 5, 3, 3, 3, 2, 2, 2, 2};
        boolean res3 = solution.makesquare(matchsticks3);
        System.out.println(res3);
    }
    
    /**
     * 要拼正方形，就相当于找出四条变长相等的数。于是该问题就转变为了给一堆数字，其中数字可以任意相加，
     * 找出相加后和相等的四个数。
     * 尝试1：说白了，越大的数越不灵活，可以先将其排序后依次加入四个数中。看看最后大小
     * 这个解法是错误的！
     */
    public boolean makesquare(int[] matchsticks) {
        if (matchsticks.length < 4) return false;
        int n = matchsticks.length;
        Arrays.sort(matchsticks);
        
        int[] fourSide = new int[4];
        fourSide[0] = matchsticks[n - 1];
        fourSide[1] = matchsticks[n - 2];
        fourSide[2] = matchsticks[n - 3];
        fourSide[3] = matchsticks[n - 4];
        
        for (int i = n - 5; i >= 0; --i) {
            // 找出四条辨中最小的一个值，加入
            int minVal = fourSide[0];
            int minIdx = 0;
            for (int j = 1; j < 4; j++) {
                if (fourSide[j] < minVal) {
                    minVal = fourSide[j];
                    minIdx = j;
                }
            }
            fourSide[minIdx] += matchsticks[i];
        }
        
        boolean res = true;
        for (int i = 1; i < 4; i++) {
            if (fourSide[i] != fourSide[i - 1]) {
                res = false;
                break;
            }
        }
        return res;
    }
    
    /**
     * dfs + 回溯算法尝试
     */
    public boolean makesquare2(int[] matchsticks) {
        int n = matchsticks.length;
        if (n < 4) return false;
        int sum = 0;
        for (int ms : matchsticks) sum += ms;
        // 判断 sum 是否可以被 4 整除
        if ((sum & 3) != 0) return false;
        return backtrack(matchsticks, 0, new int[4], sum >> 2);
    }
    
    private boolean backtrack(int[] nums, int idx, int[] four, int side) {
        if (idx >= nums.length) {
            // 如果火柴都访问完了，并且size的4个边的长度都相等，说明是正方形，直接返回true，
            // 否则返回false
            return four[0] == four[1] && four[1] == four[2] && four[2] == four[3];
        }
        
        for (int i = 0; i < 4; i++) {
            int u = nums[idx];
            // 如果把当前火柴放到 four[i] 这个边上，长度大于 side 直接跳过
            if (four[i] + u > side) continue;
            // 尝试放在 four[i] 上
            four[i] += u;
            // 然后在放下一个火柴，如果最终能变成正方形，直接返回true
            if (backtrack(nums, idx + 1, four, side)) return true;
            // 如果当前火柴放到 four[i] 这个边上，最终不能构成正方形，我们就把他从
            // four[i] 这个边上给移除，然后在试其他的边
            four[i] -= u;
        }
        // 如果不能构成正方形，直接返回 false
        return false;
    }
    
    /**
     * 倒序回溯
     */
    public boolean makesquare3(int[] nums) {
        int n = nums.length;
        if (n < 4) return false;
        int total = 0;
        for (int num : nums) total += num;
        if (total == 0 || (total & 3) != 0) return false;
        Arrays.sort(nums);
        // 回溯，从最长的火柴开始
        return backtrack3(nums, nums.length - 1, new int[4], total >> 2);
    }
    
    private boolean backtrack3(int[] nums, int idx, int[] four, int target) {
        if (idx == -1) {
            return four[0] == four[1] && four[1] == four[2] && four[2] == four[3];
        }
        for (int i = 0; i < four.length; i++) {
            // size[i] == size[i - 1] 即上一个分支的值和当前分支的一样，上一个分支没有成功，
            // 说明这个分支也不会成功，直接跳过即可。
            if (i > 0 && four[i] == four[i - 1]) continue;
            
            int u = nums[idx];
            if (four[i] + u > target) continue;
            four[i] += u;
            if (backtrack3(nums, idx - 1, four, target)) return true;
            four[i] -= u;
        }
        return false;
    }
}
