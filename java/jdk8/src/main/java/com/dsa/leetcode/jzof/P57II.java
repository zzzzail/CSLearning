package com.dsa.leetcode.jzof;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zail
 * @date 2022/7/22
 */
public class P57II {
    
    public static void main(String[] args) {
    
    }
    
    public int[][] findContinuousSequence(int target) {
        int i = 1, j = 1;   // 滑动窗口的左右边界
        int sum = 0;        // 滑动窗口中数字的和
        List<int[]> res = new ArrayList<>();
        while (i <= target / 2) {
            if (sum < target) {       // 右边界向右移动
                sum += j;
                j++;
            }
            else if (sum > target) {  // 左边界向右移动
                sum -= i;
                i++;
            }
            else {                    // sum == target 记录结果
                int[] arr = new int[j - i];
                for (int k = i; k < j; k++) {
                    arr[k - i] = k;
                }
                res.add(arr);
                // 左边界向右移动，继续找下一个可能的结果
                sum -= i;
                i++;
            }
        }
        return res.toArray(new int[res.size()][]);
    }
}
