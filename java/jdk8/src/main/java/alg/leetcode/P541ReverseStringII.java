package alg.leetcode;

/**
 * @link https://leetcode.cn/problems/reverse-string-ii/
 * 反转字符串 II
 */
public class P541ReverseStringII {
    
    public static void main(String[] args) {
        P541ReverseStringII solution = new P541ReverseStringII();
        String res1 = solution.reverseStr("abcdefg", 3);
        System.out.println(res1);
    }
    
    /**
     * 太难调了
     */
    public String reverseStr(String s, int k) {
        char[] arr = s.toCharArray();
        if (arr.length == 0 || k == 0) return s;
        for (int i = 0; i < arr.length; i += 2 * k) {
            int end = i + k - 1;
            reverse(arr, i, Math.min(end, arr.length - 1));
        }
        return String.valueOf(arr);
    }
    
    private void reverse(char[] arr, int begin, int end) {
        for (int i = begin, count = 0; i <= (begin + end) / 2; ++i, ++count) {
            char a = arr[i];
            char b = arr[end - count];
            arr[i] = b;
            arr[end - count] = a;
        }
    }
    
}
