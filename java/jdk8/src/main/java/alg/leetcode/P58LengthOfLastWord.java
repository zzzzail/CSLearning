package alg.leetcode;

/**
 * @link https://leetcode.cn/problems/length-of-last-word/
 * 最后一个单词的长度
 */
public class P58LengthOfLastWord {
    
    public static void main(String[] args) {
        P58LengthOfLastWord solution = new P58LengthOfLastWord();
        int res1 = solution.lengthOfLastWord("Hello World");
        System.out.println(res1);
    
        int res2 = solution.lengthOfLastWord("   fly me   to   the moon  ");
        System.out.println(res2);
    }
    
    /**
     * 从后向前遍历
     */
    public int lengthOfLastWord(String s) {
        if (s == null || s.length() == 0) return 0;
        
        s = s.trim();
        int i = s.length() - 1;
        for (; i >= 0; --i) {
            if (s.charAt(i) == ' ') break;
        }
        return s.length() - i - 1;
    }
}
