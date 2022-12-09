package alg.leetcode;

public class P14LongestCommonPrefix1 {
    
    public static void main(String[] args) {
        P14LongestCommonPrefix1 solution = new P14LongestCommonPrefix1();
        String[] strs1 = {"flower", "flow", "flight"};
        String res1 = solution.longestCommonPrefix(strs1);
        System.out.println(res1);
        
        String[] strs2 = {"dog", "racecar", "car", ""};
        String res2 = solution.longestCommonPrefix(strs2);
        System.out.println(res2);
    }
    
    /**
     * 看成一个矩阵 先遍历列，再遍历行即可
     */
    public String longestCommonPrefix(String[] strs) {
        int m = strs.length, n = strs[0].length();
        if (m == 1) return strs[0];
        
        int col = 0;
        for (; col < n; ++col) {
            int row = 1;
            for (; row < m; ++row) {
                if (strs[row].length() <= col) break;
                if (strs[row].charAt(col) != strs[row - 1].charAt(col)) break;
            }
            if (row != m) break;
        }
        return strs[0].substring(0, col);
    }
}
