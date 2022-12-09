package alg.leetcodejzof;

/**
 * 把数字翻译成字符串
 *
 * @author zail
 * @link https://leetcode.cn/problems/ba-shu-zi-fan-yi-cheng-zi-fu-chuan-lcof/
 * @date 2022/7/18
 */
public class P46 {
    
    public static void main(String[] args) {
    
    }
    
    public int translateNum(int num) {
        if (num < 0) return 0;
        if (num == 0) return 1;
        
        char[] arr = String.valueOf(num).toCharArray();
        int n = arr.length;
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            char c = arr[i - 1];
            char b = arr[i - 2];
            int curnum = (b - '0') * 10 + (c - '0');
            if (10 <= curnum && curnum < 26) {
                dp[i] = dp[i - 1] + dp[i - 2];
            }
            else {
                dp[i] = dp[i - 1];
            }
        }
        return dp[n];
    }
    
}
