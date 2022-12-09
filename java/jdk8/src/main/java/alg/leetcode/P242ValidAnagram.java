package alg.leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @link https://leetcode-cn.com/problems/valid-anagram/
 * 有效字母异位词
 * 给定两个单词，两个单词其中的字母出现次数是相同的，位置可以是不同的。
 */
public class P242ValidAnagram {
    
    public static void main(String[] args) {
        P242ValidAnagram solution = new P242ValidAnagram();
        String s1 = "anagram";
        String s2 = "nagaram";
        boolean res1 = solution.isAnagram(s1, s2);
        System.out.println(res1);
    }
    
    /**
     * 1. 暴力求解
     * 遍历 s 中的字符，根据字符在 t 中找，若有相同的，则继续找，若没有则，快速结束。
     * 直到找完 s 中的所有字符为止。
     * 重复元素？
     * 用一个数组，记录 t 中的字符是否已使用，在查找的过程中不可以使用已使用的字符。
     * 时间复杂度：O(s * t)
     * 空间复杂度：O(t)
     * 2. 利用 HashMap 求解，现将 s 中的所有字符加到到 HashMap 中，key 为字符本身，val 为
     * 出现的次数。
     * 后将 t 中的字符也依次加入到，但是过程中是减去出现的次数
     * 若某个字符没有在 HashMap 中则直接返回 false
     * 若某个字符减去 val 后 < 0 则说明 t 中出现次数多了，并返回 false
     * 时间复杂度：O(s)
     * 空间复杂度：O(s)
     * 3. 长度相等 && 数值相同 就可以看作是异位词吗？
     * 不可以!
     */
    public boolean isAnagram(String s, String t) {
        if (s == null || t == null) return false;
        if (s.length() != t.length()) return false;
        
        Map<Byte, Integer> map = new HashMap<>();
        for (Byte b : s.getBytes()) {
            Integer val = map.get(b);
            map.put(b, val == null ? 1 : val + 1);
        }
        for (Byte b : t.getBytes()) {
            Integer val = map.get(b);
            if (val == null) return false;
            --val;
            if (val < 0) return false;
            map.put(b, val);
        }
        return true;
    }
    
    /**
     * 2 路快排 12 ms 左右
     */
    public boolean isAnagram2(String s, String t) {
        char[] ca1 = s.toCharArray();
        char[] ca2 = t.toCharArray();
        quickSort(ca1);
        quickSort(ca2);
        return Arrays.equals(ca1, ca2);
    }
    
    private void quickSort(char[] arr) {
        if (arr == null || arr.length == 0) return;
        quickSort(arr, 0, arr.length - 1);
    }
    
    private void quickSort(char[] arr, int begin, int end) {
        if (begin >= end) return;
        int pivot = partition(arr, begin, end);
        quickSort(arr, begin, pivot - 1);
        quickSort(arr, pivot + 1, end);
    }
    
    private int partition(char[] arr, int begin, int end) {
        swap(arr, begin, (int) (Math.random() * (end - begin + 1)) + begin);
        char pivotVal = arr[begin];
        int i = begin + 1, j = end;
        while (true) {
            while (i <= end && arr[i] < pivotVal) ++i;
            while (j >= begin + 1 && arr[j] > pivotVal) --j;
            if (i > j) break;
            swap(arr, i, j);
            ++i;
            --j;
        }
        swap(arr, begin, j);
        return j;
    }
    
    /**
     * 利用排序解决
     * 3 路快排 6ms 左右，比 2 路快排快一倍
     */
    public boolean isAnagram3(String s, String t) {
        char[] ca1 = s.toCharArray();
        char[] ca2 = t.toCharArray();
        quickSort3Ways(ca1);
        quickSort3Ways(ca2);
        return Arrays.equals(ca1, ca2);
    }
    
    private void quickSort3Ways(char[] arr) {
        if (arr == null || arr.length == 0) return;
        quickSort3Ways(arr, 0, arr.length - 1);
    }
    
    // 3 路快排
    private void quickSort3Ways(char[] arr, int begin, int end) {
        if (begin >= end) return;
        
        swap(arr, begin, (int) (Math.random() * (end - begin + 1)) + begin);
        char pivotVal = arr[begin];
        int i = begin + 1, lt = begin, gt = end + 1;
        while (i < gt) {
            if (arr[i] < pivotVal) {
                swap(arr, i, ++lt);
                ++i;
            }
            else if (arr[i] > pivotVal) {
                swap(arr, i, --gt);
            }
            else { // arr[i] == pivotVal
                ++i;
            }
        }
        swap(arr, begin, lt);
        quickSort(arr, begin, lt - 1);
        quickSort(arr, gt, end);
    }
    
    private void swap(char[] arr, int a, int b) {
        char t = arr[a];
        arr[a] = arr[b];
        arr[b] = t;
    }
}
