package alg.leetcode;

import java.util.*;

/**
 * 括号生成
 *
 * @link https://leetcode-cn.com/problems/generate-parentheses/
 */
public class P22GenerateParentheses {
    
    public static void main(String[] args) {
        List<String> res1 = generateParenthesis2(1);
        System.out.println(res1);
        List<String> res2 = generateParenthesis2(2);
        System.out.println(res2);
        List<String> res3 = generateParenthesis2(3);
        System.out.println(res3);
        List<String> res4 = generateParenthesis2(4);
        System.out.println(res4);
    }
    
    /**
     * 深度优先遍历
     * 时间复杂度：O(\frac{4^n}{\sqrt{n}})
     * 空间复杂度：O(\frac{4^n}{\sqrt{n}})
     * 这个时间复杂度和卡特兰数有关
     */
    public static List<String> generateParenthesis(int n) {
        if (n <= 0) return null;
        List<String> res = new LinkedList<>();
        dfs(n, "", 0, 0, res);
        return res;
    }
    
    public static void dfs(int n, String path, int left, int right, List<String> res) {
        /*
        这里就是剪枝的操作
        在保证左括号的数量一定是小于等于 n 这个前提下，也要保证右括号数量是小于 n 的。
        结束条件： left > n || right > n
        另外，右括号的数量和左括号是相匹配的，即左括号为 2 <= n 个的时候右括号即使是 3 <= n 也是一个错误的
        括号组合。所以，也要判断 right > left 这个条件作为结束条件。
        综合： left > n || right > n || right > left
        在保证左括号不大于 n 的条件下，右括号的数量不大于左括号的数量，也就相当于不大于 n，所以 right > n
        这个条件可以省略。
         */
        if (left > n || right > left) return;
        // n * 2 为递归的深度（n 对括号有 n * 2 个字符）
        if (path.length() == n * 2) {
            res.add(path);
            return;
        }
        dfs(n, path + "(", left + 1, right, res);
        dfs(n, path + ")", left, right + 1, res);
    }
    
    /**
     * BFS
     */
    public static List<String> generateParenthesis2(int n) {
        List<String> res = new ArrayList<>();
        if (n == 0) return res;
        
        Deque<Node> queue = new LinkedList<>();
        queue.offer(new Node("", 0, 0));
        while (!queue.isEmpty()) {
            Node curNode = queue.poll();
            if (curNode.left == n && curNode.right == n) {
                res.add(curNode.res);
            }
            if (curNode.left < n) {
                queue.offer(new Node(curNode.res + "(", curNode.left + 1, curNode.right));
            }
            if (curNode.right < n && curNode.right < curNode.left) {
                queue.offer(new Node(curNode.res + ")", curNode.left, curNode.right + 1));
            }
        }
        return res;
    }
    
    /**
     * 这是从 AcWing y 总那学的逻辑
     *
     * 一组括号是格式正确的括号，它的充分必要条件有两个
     * 1. 任意前缀的 ( 数 >= ) 数
     * 2. 左右括号数量相等
     *
     * dfs 的核心思想
     * * 任意前缀只要满足一个条件，即 ( 数比 n 小，则就可以追加 (
     * * 任意前缀只要满足 ) 数比 n 小且 ( 数 > ) 数
     */
    public static List<String> generateParenthesis3(int n) {
        List<String> res = new ArrayList<>();
        dfs3(n, 0, 0, "", res);
        return res;
    }
    
    private static void dfs3(int n, int lc, int rc, String path, List<String> res) {
        if (lc == n && rc == n) {
            res.add(path);
            return;
        }
        
        if (lc < n) dfs3(n, lc + 1, rc, path + "(", res);
        if (rc < n && lc > rc) dfs3(n, lc, rc + 1, path + ")", res);
    }
    
    static class Node {
        // 当前得到的字符串
        private String res;
        // 左括号数量
        private int left;
        // 右括号数量
        private int right;
        public Node(String str, int left, int right) {
            this.res = str;
            this.left = left;
            this.right = right;
        }
    }
    
}
