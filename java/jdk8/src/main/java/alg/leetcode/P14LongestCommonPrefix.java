package alg.leetcode;

public class P14LongestCommonPrefix {
    
    public static void main(String[] args) {
        P14LongestCommonPrefix solution = new P14LongestCommonPrefix();
        String[] strs1 = {"flower", "flow", "flight"};
        String res1 = solution.longestCommonPrefix(strs1);
        System.out.println(res1);
        
        String[] strs2 = {"dog", "racecar", "car", ""};
        String res2 = solution.longestCommonPrefix(strs2);
        System.out.println(res2);
    }
    
    /**
     * 将所有单词看成一个由所有单词首字母共同开始，各自结束的字母矩阵。
     * 而后先遍历列，遍历完列后可判断出第一列中出现的字母是否都一致，若一致则加入到最长公共前缀中，
     * 若不一致则结束遍历。
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
    
    // 不用 substring 方法实现（substring 会消耗更多的性能）
    public String longestCommonPrefix2(String[] strs) {
        int m = strs.length, n = strs[0].length();
        if (m == 0) return "";
        if (m == 1) return strs[0];
        
        StringBuilder sb = new StringBuilder();
        int col = 0;
        for (; col < n; col++) {
            int row = 1;
            for (; row < m; row++) {
                if (strs[row].length() <= col || strs[0].charAt(col) != strs[row].charAt(col)) {
                    return sb.toString();
                }
            }
            sb.append(strs[0].charAt(col));
        }
        return sb.toString();
    }
}
