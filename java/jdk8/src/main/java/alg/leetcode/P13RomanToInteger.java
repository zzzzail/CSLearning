package alg.leetcode;

/**
 * 罗马数字转整数
 *
 * @link https://leetcode.cn/problems/roman-to-integer/
 * @author zhangxq
 * @since 2022/12/9
 */
public class P13RomanToInteger {
    
    public static void main(String[] args) {
        String s1 = "III";
        int res1 = romanToInt(s1);
        System.out.println(res1);
    }
    
    public static int romanToInt(String s) {
        // 把阿拉伯数字与罗马数字可能出现的所有情况和对应关系，放在两个数组中，并且按照阿拉伯数字的大小降序排列
        int[]    nums =   {1000, 900,  500, 400,  100, 90,   50,  40,   10,   9,   5,   4,    1};
        String[] romans = {"M",  "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
    
        int res = 0, idx = 0;
        while (idx < nums.length) {
            while (s.startsWith(romans[idx])) {
                s = s.substring(romans[idx].length());
                res += nums[idx];
            }
            idx++;
        }
        return res;
    }
}
