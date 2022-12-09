package alg.leetcode;

/**
 * @link https://leetcode.cn/problems/valid-palindrome/
 * 验证回文串
 */
public class P125ValidPalindrome {
    
    public static void main(String[] args) {
        P125ValidPalindrome solution = new P125ValidPalindrome();
        boolean res1 = solution.isPalindrome("race a car");
        System.out.println(res1);
    }
    
    /**
     * 双指针
     */
    public boolean isPalindrome(String s) {
        s = s.trim();
        if (s.length() == 0) return true;
        
        int left = 0, right = s.length() - 1;
        while (left < right) {
            // 过掉不是字母或数字的
            while (left < right && !Character.isLetterOrDigit(s.charAt(left))) ++left;
            while (left < right && !Character.isLetterOrDigit(s.charAt(right))) --right;
            if (Character.toLowerCase(s.charAt(left)) != Character.toLowerCase(s.charAt(right))) {
                return false;
            }
            ++left;
            --right;
        }
        return true;
    }
    
}
