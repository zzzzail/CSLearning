package alg.leetcodejzof;

/**
 * 机器人的运动范围
 *
 * @author zail
 * @link https://leetcode.cn/problems/ji-qi-ren-de-yun-dong-fan-wei-lcof/
 * @date 2022/7/19
 */
public class P13 {
    
    public static void main(String[] args) {
        P13 solution = new P13();
        
        int res1 = solution.movingCount(2, 3, 1);
        System.out.println(res1);
    }
    
    private int count = 0;
    private boolean[][] visited;
    
    public int movingCount(int m, int n, int k) {
        visited = new boolean[m][n];
        dfs(m, n, 0, 0, k);
        return count;
    }
    
    private void dfs(int m, int n, int row, int col, int k) {
        if (!(0 <= row && row < m && 0 <= col && col < n)) return;
        if (visited[row][col]) return;
        visited[row][col] = true;
        if ((digitSum(row) + digitSum(col)) > k) return;
        count++;
        dfs(m, n, row - 1, col, k);
        dfs(m, n, row + 1, col, k);
        dfs(m, n, row, col - 1, k);
        dfs(m, n, row, col + 1, k);
    }
    
    private int digitSum(int num) {
        int res = num % 10;
        while (num > 0) {
            num = num / 10;
            res += num;
        }
        return res;
    }
}
