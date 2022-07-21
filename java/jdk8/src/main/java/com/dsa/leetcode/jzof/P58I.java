package com.dsa.leetcode.jzof;

/**
 * 翻转单词顺序
 *
 * @author zail
 * @link https://leetcode.cn/problems/fan-zhuan-dan-ci-shun-xu-lcof/
 * @date 2022/7/19
 */
public class P58I {
    
    public static void main(String[] args) {
    
    }
    
    /**
     * 先切断再前后交换
     */
    public String reverseWords(String s) {
        if (s == null) return null;
        
        String[] arr = s.trim().split("\\s+");
        int left = 0, right = arr.length - 1;
        while (left < right) {
            String t = arr[left];
            arr[left] = arr[right];
            arr[right] = t;
            left++;
            right--;
        }
        return String.join(" ", arr);
    }
}
