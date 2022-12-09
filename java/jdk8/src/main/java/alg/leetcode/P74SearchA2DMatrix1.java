package alg.leetcode;

public class P74SearchA2DMatrix1 {
    
    public static void main(String[] args) {
        int[][] matrix1 = new int[][]{
                {1, 3, 5, 7},
                {10, 11, 16, 20},
                {23, 30, 34, 60}};
        int target1 = 3;
        boolean res1 = searchMatrix(matrix1, target1);
        System.out.println(res1);
        
        System.out.println(searchMatrix(matrix1, 60));
    }
    
    /**
     * 二分查找
     */
    public static boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length;
        int n = matrix[0].length;
        int begin = 0, end = m * n - 1;
        while (begin <= end) {
            int mid = (begin + end) >> 1;
            int i = mid / n, j = mid % n;
            if (matrix[i][j] == target) return true;
            else if (target < matrix[i][j]) end = mid - 1;
            else begin = mid + 1;
        }
        return false;
    }
    
    /**
     * 从右上角到左下角搜索
     */
    public static boolean searchMatrix2(int[][] matrix, int target) {
        if (matrix.length == 0) return false;
        int row = 0, col = matrix[0].length - 1;
        while (row < matrix.length && col >= 0) {
            if (matrix[row][col] < target) ++row;
            else if (matrix[row][col] > target) --col;
            else return true;
        }
        return false;
    }
    
}
