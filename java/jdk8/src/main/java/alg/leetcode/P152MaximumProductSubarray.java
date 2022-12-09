package alg.leetcode;

public class P152MaximumProductSubarray {
    
    public static void main(String[] args) {
    
    
    }
    
    /**
     * 动态规划
     * 特殊情况：负负相乘得正
     */
    public int maxProduct(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        
        int n = nums.length;
        int[] dpMax = new int[n];
        int[] dpMin = new int[n];
        dpMax[0] = nums[0];
        dpMin[0] = nums[0];
        int maxValue = nums[0];
        for (int i = 1; i < n; i++) {
            if (nums[i] >= 0) { // 正数
                dpMax[i] = Math.max(dpMax[i - 1] * nums[i], nums[i]);
                dpMin[i] = Math.min(dpMin[i - 1] * nums[i], nums[i]);
            }
            else {
                dpMax[i] = Math.max(dpMin[i - 1] * nums[i], nums[i]);
                dpMin[i] = Math.min(dpMax[i - 1] * nums[i], nums[i]);
            }
            maxValue = Math.max(maxValue, dpMax[i]);
        }
        return maxValue;
    }
    
}
