package alg.leetcode;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class P77Combinations2 {
    
    public static void main(String[] args) {
        combine(4, 2);
    }
    
    /**
     * 返回范围内的所有 k 个数的组合
     * n = 4, k = 2
     * [
     *   [1, 2],
     *   [1, 3],
     *   [1, 4],
     *   [2, 3],
     *   [2, 4],
     *   [3, 4]
     * ]
     */
    public static List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> res = new LinkedList<>();
        if (k <= 0 || k < n) {
            return res;
        }
        Deque<Integer> path = new LinkedList<>();
        // 深度优先遍历?
        dfs(n, k, 1, path, res);
        return res;
    }
    
    /**
     * 深度优先遍历
     * @param n      1 ... n 结束点在哪
     * @param k      每个结果集共有多少个元素
     * @param begin  当前递归是从哪个数字开始的
     * @param path   路径递归路径
     * @param res    结果集
     */
    private static void dfs(int n, int k, int begin, Deque<Integer> path, List<List<Integer>> res) {
        if (path.size() == k) { // 递归的结束条件
            res.add(new LinkedList<>(path));
            return;
        }
        // 遍历每一个结点
        for (int i = begin; i <= n; ++i) {
            path.addLast(i);
            dfs(n, k, i + 1, path, res);
            path.removeLast(); // 这一步要与递归之前的一步做相反的操作
        }
    }
    
}
