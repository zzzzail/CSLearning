package com.java.dsa.leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * @link https://leetcode.cn/problems/jewels-and-stones/
 * 宝石与石头
 */
public class P771JewelsAndStones1 {
    
    public static void main(String[] args) {
    
    }
    
    /**
     * 使用 HashSet 保存 jewels 中的每一个字符
     */
    public int numJewelsInStones(String jewels, String stones) {
        if (jewels == null || jewels.length() == 0) return 0;
        if (stones == null || stones.length() == 0) return 0;
        
        Set<Character> set = new HashSet<>();
        for (int i = 0; i < jewels.length(); i++) {
            set.add(jewels.charAt(i));
        }
        
        int count = 0;
        for (int i = 0; i < stones.length(); ++i) {
            if (set.contains(stones.charAt(i))) ++count;
        }
        return count;
    }
    
    private Set<Character> set;
    private int count = 0;
    // 二分
    public int numJewelsInStones2(String jewels, String stones) {
        if (jewels == null || jewels.length() == 0) return 0;
        if (stones == null || stones.length() == 0) return 0;
    
        set = new HashSet<>(jewels.length());
        for (int i = 0; i < jewels.length(); i++) {
            set.add(jewels.charAt(i));
        }
        binaryCount(stones.toCharArray(), 0, stones.length() - 1);
        return count;
    }
    
    private void binaryCount(char[] arr, int low, int high) {
        if (low == high) {
            if (set.contains(arr[low])) ++count;
            return;
        }
        int mid = (low + high) >> 1;
        binaryCount(arr, low, mid);
        binaryCount(arr, mid + 1, high);
    }
}
