package com.dsa.leetcode;

/**
 * @author zail
 * @date 2022/5/27
 */
public class P1711FindClosestIcci1 {
    
    public static void main(String[] args) {
        String[] words1 = {"I", "am", "a", "student", "from", "a", "university", "in", "a", "city"};
        P1711FindClosestIcci1 solution = new P1711FindClosestIcci1();
        int res1 = solution.findClosest(words1, "a", "student");
        System.out.println(res1);
    }
    
    public int findClosest(String[] words, String word1, String word2) {
        int ans = 100001;
        int p1 = -1, p2 = -1;
        for (int i = 0; i < words.length; i++) {
            if (words[i].equals(word1)) {
                p1 = i;
                if (p2 != -1) ans = Math.min(ans, p1 - p2);
            }
            else if (words[i].equals(word2)) {
                p2 = i;
                if (p1 != -1) ans = Math.min(ans, p2 - p1);
            }
        }
        return ans;
    }
    
}
