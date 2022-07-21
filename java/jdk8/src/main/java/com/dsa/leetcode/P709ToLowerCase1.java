package com.dsa.leetcode;

/**
 * @link https://leetcode.cn/problems/to-lower-case/
 * 转换成小写字母
 */
public class P709ToLowerCase1 {
    
    public static void main(String[] args) {
        // System.out.println('a' - 'A'); // 32
        P709ToLowerCase1 solution = new P709ToLowerCase1();
        String res1 = solution.toLowerCase2("Hello");
        System.out.println(res1);
        
    }
    
    private final int distance = 'a' - 'A';
    
    // 暴力
    public String toLowerCase(String s) {
        if (s == null || s.length() == 0) return "";
        
        byte[] res = new byte[s.length()];
        for (int i = 0; i < s.length(); i++) {
            byte b = s.getBytes()[i];
            if (isUpper(b)) b += distance;
            res[i] = b;
        }
        return new String(res);
    }
    
    public boolean isUpper(byte b) {
        return 'A' <= b && b <= 'Z';
    }
    
    /**
     * 二分
     * 时间复杂度： O(logn)
     */
    public String toLowerCase2(String s) {
        if (s == null || s.length() == 0) return "";
        
        byte[] res = new byte[s.length()];
        binaryToLowerCase(s.getBytes(), 0, s.length() - 1, res);
        return new String(res);
    }
    
    private void binaryToLowerCase(byte[] arr, int low, int high, byte[] res) {
        if (low == high) {
            res[low] = arr[low];
            if (isUpper(res[low])) res[low] += distance;
            return;
        }
        int mid = (low + high) >> 1;
        binaryToLowerCase(arr, low, mid, res);
        binaryToLowerCase(arr, mid + 1, high, res);
    }
    
}
