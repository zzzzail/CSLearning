package alg.leetcode;

/**
 * @author zhangxq
 * @since 2022/10/24
 */
public class P5LongestPalindromicSubstring02 {
    
    public static void main(String[] args) {
    
    }
    
    public String longestPalindrome(String s) {
        String res = "";
        for (int i = 0; i < s.length(); i++) {
            // 如果回文字符串字符个数是奇数的情况
            int l = i - 1, r = i + 1;
            while (l >= 0 && r < s.length() && s.charAt(l) == s.charAt(r)) {
                l--;
                r++;
            }
            if (res.length() < r - l - 1) res = s.substring(l + 1, r);
            
            // 如果回文字符串字符个数是偶数的情况
            l = i; r = i + 1;
            while (l >= 0 && r < s.length() && s.charAt(l) == s.charAt(r)) {
                l--;
                r++;
            }
            if (res.length() < r - l - 1) res = s.substring(l + 1, r);
        }
        return res;
    }
    
}
