package alg;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class KMP {
    
    public static void main(String[] args) {
        KMP kmp = new KMP();
        int[] res1 = kmp.prefixTable("ABABCABAA");
        System.out.println(Arrays.toString(res1));
        kmp.movePrefixTable(res1);
        System.out.println(Arrays.toString(res1));
    
        List<Integer> indexes = kmp.kmpSearch("ABCABABCA", "ABC");
        System.out.println(indexes);
    }
    
    public List<Integer> kmpSearch(String text, String pattern) {
        List<Integer> res = new ArrayList<>();
        int[] prefixTable = prefixTable(pattern);
        movePrefixTable(prefixTable);
        
        int m = text.length(), n = pattern.length();
        int i = 0, j = 0;
        while (i < m) {
            // 匹配到了
            if (j == n - 1 && text.charAt(i) == pattern.charAt(j)) {
                res.add(i - j);
                j = prefixTable[j];
            }
            if (text.charAt(i) == pattern.charAt(j)) {
                ++i;
                ++j;
            }
            else {
                j = prefixTable[j];
                if (j == -1) {
                    ++i;
                    ++j;
                }
            }
        }
        return res;
    }
    
    private void movePrefixTable(int[] prefixTable) {
        int n = prefixTable.length;
        // System.arraycopy(prefixTable, 0, prefixTable, 1, n - 1);
        for (int i = n - 1; i > 0; --i) {
            prefixTable[i] = prefixTable[i - 1];
        }
        prefixTable[0] = -1;
    }
    
    private int[] prefixTable(String pattern) {
        int n = pattern.length();
        int[] prefixTable = new int[n];
        if (pattern.length() == 0) return prefixTable;
        
        int len = 0;
        int i = 1;
        while (i < n) {
            if (pattern.charAt(len) == pattern.charAt(i)) {
                ++len;
                prefixTable[i] = len;
                ++i;
            }
            else {
                if (len > 0) {
                    len = prefixTable[len - 1];
                }
                else {
                    prefixTable[i] = len;
                    ++i;
                }
            }
        }
        return prefixTable;
    }
}
