package alg.leetcodejzof;

/**
 * 左旋转字符串
 *
 * @author zail
 * @link https://leetcode.cn/problems/zuo-xuan-zhuan-zi-fu-chuan-lcof/
 * @date 2022/7/4
 */
public class P58 {
    
    public static void main(String[] args) {
    
    }
    
    public String reverseLeftWords(String s, int n) {
        if (s == null) return "";
        if (n >= s.length()) return s;
        
        StringBuilder tail = new StringBuilder();
        for (int i = 0; i < n; i++) {
            tail.append(s.charAt(i));
        }
        StringBuilder prefix = new StringBuilder();
        for (int i = n; i < s.length(); i++) {
            prefix.append(s.charAt(i));
        }
        return prefix.toString() + tail.toString();
    }
}
