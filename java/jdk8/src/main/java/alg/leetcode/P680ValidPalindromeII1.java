package alg.leetcode;

/**
 * @link https://leetcode.cn/problems/valid-palindrome-ii/
 * 验证回文字符串 II
 */
public class P680ValidPalindromeII1 {
    
    public static void main(String[] args) {
        P680ValidPalindromeII1 solution = new P680ValidPalindromeII1();
        // boolean res1 = solution.validPalindrome2("aba");
        // System.out.println(res1);
        // boolean res2 = solution.validPalindrome2("abca");
        // System.out.println(res2);
        // boolean res3 = solution.validPalindrome2("");
        // System.out.println(res3);
        // boolean res4 = solution.validPalindrome2("a");
        // System.out.println(res4);
        // boolean res5 = solution.validPalindrome2("abab");
        // System.out.println(res5);
        boolean res6 = solution.validPalindrome2("eeccccbebaeeabebccceea");
        // "cupuufxoohdfpgjdmysgvhmvffcnqxjjxqncffvmhvgsymdjgpfdhooxfuupucu"
        System.out.println(res6);
    }
    
    /**
     * 暴力删除某个字符，判断是否是回文字符串
     */
    public boolean validPalindrome(String s) {
        if (s.length() <= 1) return true;
        if (isPalindrome(s.toCharArray(), -1)) return true;
        for (int i = 0; i < s.length(); i++) {
            if (isPalindrome(s.toCharArray(), i)) {
                return true;
            }
        }
        return false;
    }
    
    public boolean isPalindrome(char[] arr, int without) {
        int left = 0, right = arr.length - 1;
        while (left < right) {
            if (without == left) ++left;
            if (without == right) --right;
            if (arr[left] != arr[right]) {
                return false;
            }
            ++left;
            --right;
        }
        return true;
    }
    
    public boolean isPalindrome(String s, int left, int right) {
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }
            ++left;
            --right;
        }
        return true;
    }
    
    public boolean validPalindrome2(String s) {
        int left = 0, right = s.length() - 1;
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                // 尝试删去 left，也就是判断 s[left + 1] = s[right]
                // 尝试删去 right，也就是判断 s[left] = s[right - 1]
                return isPalindrome(s, left + 1, right) || isPalindrome(s, left, right - 1);
            }
            ++left;
            --right;
        }
        return true;
    }
}
