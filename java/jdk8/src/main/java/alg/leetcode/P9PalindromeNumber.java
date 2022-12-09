package alg.leetcode;

/**
 * 回文数
 *
 * @link https://leetcode.cn/problems/palindrome-number/
 * @author zhangxq
 * @since 2022/12/9
 */
public class P9PalindromeNumber {
    
    public static void main(String[] args) {
    
    }
    
    public boolean isPalindrome(int x) {
        if ( x < 0 ) return false;
        if ( x != 0 && x % 10 == 0) return false; // 尾数为0
        // 倒过来的数字（如果使用 int 类型，这里会发生数字溢出）
        long reverse = 0;
        int h = x;
        while ( h > 0 ) {
            reverse = ( reverse * 10 ) + ( h % 10 );
            h /= 10;
        }
        return x == reverse;
    }
}
