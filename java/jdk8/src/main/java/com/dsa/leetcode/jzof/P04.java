package com.dsa.leetcode.jzof;

/**
 * 二维数组中的查找
 *
 * @author zail
 * @link https://leetcode.cn/problems/er-wei-shu-zu-zhong-de-cha-zhao-lcof/
 * @date 2022/7/17
 */
public class P04 {
    
    public static void main(String[] args) {
        P04 solution = new P04();
        int[][] matrix1 = new int[][]{{1, 4, 7, 11, 15}, {2, 5, 8, 12, 19}, {3, 6, 9, 16, 22}, {10, 13, 14, 17, 24}, {18, 21, 23, 26, 30}};
        boolean res1 = solution.findNumberIn2DArray(matrix1, 5);
        System.out.println(res1);
        boolean res2 = solution.findNumberIn2DArray(matrix1, 20);
        System.out.println(res2);
        
        int[][] matrix2 = new int[][]{{-1, 3}};
        System.out.println(solution.findNumberIn2DArray(matrix2, 3));
        System.out.println(solution.findNumberIn2DArray(matrix2, 2));
        
        int[][] matrix3 = new int[][]{{1, 2, 3, 4, 5}, {6, 7, 8, 9, 10}, {11, 12, 13, 14, 15}, {16, 17, 18, 19, 20}, {21, 22, 23, 24, 25}};
        System.out.println(solution.findNumberIn2DArray(matrix3, 15));
        System.out.println(solution.findNumberIn2DArray(matrix3, 30));
        
        int[][] matrix4 = new int[][]{{2, 5}, {2, 8}, {7, 9}, {7, 11}, {9, 11}};
        System.out.println(solution.findNumberIn2DArray(matrix4, 7));
        System.out.println(solution.findNumberIn2DArray(matrix4, 12));
        
        int[][] matrix5 = new int[][]{{5, 6, 10, 14}, {6, 10, 13, 18}, {10, 13, 18, 19}};
        System.out.println(solution.findNumberIn2DArray(matrix5, 14));
        System.out.println(solution.findNumberIn2DArray(matrix5, 20));
    }
    
    /**
     * 在一个 m 行 n 列的有序数组中查找 target 值。
     * 对角线查找方式虽然快，但不可取，会出现漏查的情况。
     */
    public boolean findNumberIn2DArray(int[][] matrix, int target) {
        if (matrix == null) return false;
        
        int m = matrix.length;
        if (m == 0) return false;
        int n = matrix[0].length;
        // 先查找对角线，查找到 matrix[i][j] > target 的位置
        int i = 0, j = 0;
        for (; i < m && j < n; i++, j++) {
            if (matrix[i][j] > target) break;
        }
        // 开始就找不到
        if (i == 0 && j == 0) {
            return false;
        }
        else if (i > 0 && i < m && j > 0 && j < n) {
            // 之后再从 i - 1, j - 1 处依次向后查找到 i, j
            int beginI = i - 1, beginJ = j - 1;
            while (beginI < m && beginJ < n) {
                // 不在 matrix[i - 1][j - 1] ~ matrix[i][j] 区间中的值就返回错误
                if (beginI == i && beginJ == j) break;
                if (matrix[beginI][beginJ] == target) return true;
                if (beginJ + 1 == n) beginI++;
                beginJ = (beginJ + 1) % n;
            }
        }
        
        // 如果找到对角线最后有一行还没有找到，则继续从对角线向后查找
        i--;
        j--;
        while (i < m && j < n) {
            if (matrix[i][j] == target) return true;
            if (j + 1 == n) i++;
            j = (j + 1) % n;
        }
        return false;
    }
    
    public boolean findNumberIn2DArray2(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return false;
        
        int m = matrix.length, n = matrix[0].length;
        int i = 0, j = n - 1;
        // 查找时一直是向左向下查找
        while (i < m && j >= 0) {
            if (matrix[i][j] == target) {
                return true;
            }
            else if (matrix[i][j] < target) { // 当前元素 < target，向下行数加一
                i++;
            }
            else { // if (matrix[i][j] > target){ // 当前元素 > target 向左列数减一
                j--;
            }
        }
        return false;
    }
}
