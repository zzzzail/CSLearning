package alg.leetcode;

/**
 * @link https://leetcode.cn/problems/reverse-string/
 * 翻转字符串
 */
public class P344ReverseString1 {
    
    public static void main(String[] args) {
        P344ReverseString1 solution = new P344ReverseString1();
        char[] s1 = {'h', 'e', 'l', 'l', 'o'};
        solution.reverseString(s1);
        System.out.println(s1);
    }
    
    public void reverseString(char[] s) {
        if (s == null || s.length <= 1) return;
        for (int i = 0, j = s.length - 1; i < j; ++i, --j) {
            swap(s, i, j);
        }
    }
    
    private void swap(char[] s, int i1, int i2) {
        char t = s[i1];
        s[i1] = s[i2];
        s[i2] = t;
    }
    
    /**
     * 0 ms 的代码
     */
    public void reverseString2(char[] s) {
        for (int i = 0; i < s.length / 2; ++i) {
            char a = s[i];
            char b = s[s.length - 1 - i];
            s[i] = b;
            s[s.length - 1 - i] = a;
        }
    }
    
}
