package com.dsa.leetcode;

import java.util.Arrays;

/**
 * https://leetcode-cn.com/problems/plus-one/
 * 加一
 * 给定一个由整数组成的非空数组所表示的非负整数，在该数的基础上加一。
 * 最高位数字存放在数组的首位， 数组中每个元素只存储单个数字。
 * 你可以假设除了整数 0 之外，这个整数不会以零开头。
 * <p>
 * 向上溢出怎么办？
 * [9, 9] + 1 = [1, 0, 0]
 */
public class P66PlusOne1 {
    
    public static void main(String[] args) {
        int[] digits = new int[]{9, 9, 9};
        int[] res1 = plusOne(digits);
        System.out.println(Arrays.toString(res1));
    }
    
    public static int[] plusOne(int[] digits) {
        // 先 + 1，再进位
        digits[digits.length - 1] = digits[digits.length - 1] + 1;
        int i = digits.length - 1;
        while (i > 0 && digits[i] >= 10) {
            int carry = digits[i] / 10;
            digits[i] = digits[i] % 10;
            digits[i - 1] = digits[i - 1] + carry;
            --i;
        }
        
        int[] res;
        if (digits[0] >= 10) {
            res = new int[digits.length + 1];
            System.arraycopy(digits, 0, res, 1, digits.length);
            res[0] = digits[0] / 10;
            res[1] = res[1] % 10;
        }
        else {
            res = new int[digits.length];
            System.arraycopy(digits, 0, res, 0, digits.length);
        }
        return res;
    }
}
