package alg.leetcode;

/**
 * @author zail
 * @date 2022/5/29
 */
public class P12IntegerToRoman {
    
    /**
     * 贪心算法
     */
    public String intToRoman(int num) {
        // 把阿拉伯数字与罗马数字可能出现的所有情况和对应关系，放在两个数组中，并且按照阿拉伯数字的大小降序排列
        int[] nums = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] romans = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
    
        StringBuilder sb = new StringBuilder();
        int idx = 0;
        while (idx < nums.length) {
            while (num >= nums[idx]) {
                sb.append(romans[idx]);
                num -= nums[idx];
            }
            ++idx;
        }
        return sb.toString();
    }
    
}
