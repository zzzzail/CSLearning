package alg.leetcode;

import java.util.Arrays;

public class P455AssignCookies1 {
    
    public static void main(String[] args) {
    
    }
    
    /**
     * 先用大的饼干满足大胃口的孩子
     */
    public int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);
        int index = s.length - 1; // 饼干数组的下表
        int result = 0;
        for (int i = g.length - 1; i >= 0; --i) {
            if (index >= 0 && s[index] >= g[i]) {
                result++;
                index--;
            }
        }
        return result;
    }
    
    /**
     * 小饼干先喂饱小胃口
     */
    public static int findContentChildren2(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);
        int index = 0;
        for (int i = 0; i < s.length; ++i) {
            if (index < g.length && g[index] <= s[i]) {
                index++;
            }
        }
        return index;
    }
    
}
