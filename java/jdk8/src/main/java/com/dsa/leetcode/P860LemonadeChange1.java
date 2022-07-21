package com.dsa.leetcode;

/**
 * @link https://leetcode-cn.com/problems/lemonade-change/
 * 柠檬水找零
 */
public class P860LemonadeChange1 {
    
    public static void main(String[] args) {
    
    }
    
    /**
     * 3 中情况
     * 1. 5 元，不找零
     * 2. 10元，找一张 5 元的零钱
     * 3. 20元，照一张 10 元，一张 5 元的零钱。如果行不通，则尝试找 3 张 5 元的零钱。（贪心）
     */
    public static boolean lemonadeChange(int[] bills) {
        int five = 0, ten = 0, twenty = 0;
        for (int bill : bills) {
            // 情况一
            if (bill == 5) ++five;
            if (bill == 10) {
                if (five <= 0) return false;
                ++ten;
                --five;
            }
            if (bill == 20) {
                // 优先消耗10美元，因为5美元的找零⽤处更⼤，能多留着就多留着
                if (ten > 0 && five > 0) {
                    ++twenty; // 其实这⾏代码可以删了，因为记录20已经没有意义了，不会⽤20来找零
                    --ten;
                    --five;
                }
                else if (five >= 3) {
                    ++twenty;
                    five -= 3;
                }
                else return false;
            }
        }
        return true;
    }
    
}
