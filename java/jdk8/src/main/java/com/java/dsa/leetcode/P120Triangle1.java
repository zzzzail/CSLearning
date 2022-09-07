package com.java.dsa.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class P120Triangle1 {
    
    public static void main(String[] args) {
        List<List<Integer>> triangle1 = new ArrayList<>();
        triangle1.add(Collections.singletonList(2));
        triangle1.add(Arrays.asList(3, 4));
        triangle1.add(Arrays.asList(6, 5, 7));
        triangle1.add(Arrays.asList(4, 1, 8, 3));
        int res1 = minimumTotal2(triangle1);
        System.out.println(res1);
    }
    
    public static int minimumTotal(List<List<Integer>> triangle) {
        return path(triangle, 0, 0);
    }
    
    /**
     * f(i,j) = min(f(i+1,j),f(i+1,j+1)) + triangle[i][j]
     */
    private static int path(List<List<Integer>> triangle, int row, int col) {
        // 递归结束条件
        if (col >= triangle.get(0).size()) return Integer.MAX_VALUE;
        if (row >= triangle.size()) return 0;
        int v1 = path(triangle, row + 1, col);
        int v2 = path(triangle, row + 1, col + 1);
        return Math.min(v1, v2) + triangle.get(row).get(col);
    }
    
    /**
     * 动态规划方式，向上递推
     */
    public static int minimumTotal2(List<List<Integer>> triangle) {
        int n = triangle.size();
        int[][] dp = new int[n + 1][n + 1];
        for (int i = n - 1; i >= 0; --i) {
            for (int j = 0; j <= i; ++j) {
                dp[i][j] = Math.min(dp[i + 1][j], dp[i + 1][j + 1]) + triangle.get(i).get(j);
            }
        }
        return dp[0][0];
    }
}
