package alg.leetcode;

/**
 * @link https://leetcode.cn/problems/reverse-words-in-a-string-iii/
 * 反转字符串中的单词 III
 */
public class P557ReverseWordsInAStringIII1 {
    
    public static void main(String[] args) {
    
    }
    
    public String reverseWords(String s) {
        char[] array = s.toCharArray();
        int start = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] == ' ') {
                reverse(array, start, i - 1);
                start = i + 1; // 更新 start 为下一个单词的左索引
                continue;
            }
            if (i == array.length - 1) {
                reverse(array, start, i);
            }
        }
        return new String(array);
    }
    
    private void reverse(char[] array, int l, int r) {
        while (l < r) {
            char temp = array[l];
            array[l] = array[r];
            array[r] = temp;
            l += 1;
            r -= 1;
        }
    }
}
