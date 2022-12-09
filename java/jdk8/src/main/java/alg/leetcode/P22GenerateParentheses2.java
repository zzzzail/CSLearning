package alg.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @link https://leetcode-cn.com/problems/generate-parentheses/
 * 括号生成
 */
public class P22GenerateParentheses2 {
    
    public static void main(String[] args) {
        List<String> res1 = generateParenthesis(1);
        System.out.println(res1);
        List<String> res2 = generateParenthesis(2);
        System.out.println(res2);
        List<String> res3 = generateParenthesis(3);
        System.out.println(res3);
    }
    
    public static List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        if (n <= 0) return res;
        dfs(n, "", 0, 0, res);
        return res;
    }
    
    private static void dfs(int n, String path, int left, int right, List<String> res) {
        if (left > n || right > left) return;
        if (path.length() == n * 2) {
            res.add(path);
            return;
        }
        dfs(n, path + "(", left + 1, right, res);
        dfs(n, path + ")", left, right + 1, res);
    }
    
}
