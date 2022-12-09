package alg.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Z 字形变换
 *
 * @author zail
 * @link <a href="https://leetcode.cn/problems/zigzag-conversion/">Z 字形变换</a>
 * @date 2022/5/27
 */
public class P6ZigzagConversion {
    
    public static void main(String[] args) {
    
    }
    
    public String convert(String s, int numRows) {
        if (numRows < 2) return s;
        List<StringBuilder> rows = new ArrayList<>();
        for (int i = 0; i < numRows; i++) rows.add(new StringBuilder());
        int i = 0, flag = -1;
        for (char c : s.toCharArray()) {
            rows.get(i).append(c);
            // 遇到 0 或 numRow - 1 是变换
            if (i == 0 || i == numRows - 1) flag = -flag;
            i = i + flag;
        }
        StringBuilder res = new StringBuilder();
        for (StringBuilder row : rows) res.append(row);
        return res.toString();
    }
    
    public String convert2(String s, int r) {
        String res = "";
        if (r == 1) return s;
        
        // 2 * r - 2
        for (int i = 0; i < r; i++) {
            if (i == 0 || i == r - 1) {
                for (int j = i; j < s.length(); j += 2 * r - 2) {
                    res += s.charAt(j);
                }
            }
            else {
                for (int j = i, k = 2 * r - 2; j < s.length() || k < s.length(); j += 2 * r - 2, k += 2 * r - 2) {
                    if (j < s.length()) res += s.charAt(j);
                    if (k < s.length()) res += s.charAt(k);
                }
            }
        }
        
        return res;
    }
}
