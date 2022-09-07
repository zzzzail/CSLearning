package com.java.dsa.leetcode;

import java.util.*;

/**
 * @link https://leetcode-cn.com/problems/generate-parentheses/
 * 括号生成
 */
public class P22GenerateParentheses3 {
    
    public static void main(String[] args) {
        P22GenerateParentheses3 solution = new P22GenerateParentheses3();
        List<String> res1 = solution.generateParenthesis3(3);
        System.out.println(res1);
    }
    
    /**
     * 1. 暴力： n * 2 个字符，每个字符要么是左括号，要么是右括号；
     * 依次判断字符串是否合法即可。
     * 2. DFS + 剪枝
     * 3. BFS
     */
    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        if (n == 0) return res;
        dfs(n, "", 0, 0, res);
        return res;
    }
    
    private void dfs(int n, String path, int left, int right, List<String> res) {
        if (left > n || right > left) return; // 剪枝
        if (path.length() == n * 2) {
            res.add(path);
            return;
        }
        dfs(n, path + "(", left + 1, right, res);
        dfs(n, path + ")", left, right + 1, res);
    }
    
    public List<String> generateParenthesis2(int n) {
        List<String> res = new ArrayList<>();
        if (n == 0) return res;
        Deque<Parenthesis> queue = new LinkedList<>();
        queue.offer(new Parenthesis("", 0, 0));
        // (((())
        while (!queue.isEmpty()) {
            Parenthesis p = queue.poll();
            if (p.val.length() == n * 2) {
                res.add(p.val);
                continue;
            }
            if (p.left < n) {
                queue.offer(new Parenthesis(p.val + "(", p.left + 1, p.right));
            }
            if (p.right < n && p.right < p.left) {
                queue.offer(new Parenthesis(p.val + ")", p.left, p.right + 1));
            }
        }
        return res;
    }
    
    static class Parenthesis {
        String val;
        int left;
        int right;
        
        public Parenthesis(String val, int left, int right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
    
    /**
     * 动态规划
     * 子问题： 要想求出有 i 对括号的所有结果，就要求出有 i - 1 对括号的所有结果，在 i - 1 对
     * 括号的结果上加上一对括号即可。
     * DP 数组定义： dp[i][str] 是有 i 个括号的所有结果字符串
     * DP 方程： 设计加括号的方程为 ( + x + ) + y
     * 其中 x 从第 0 行的结果开始依次，加到 i - 1 行。
     * y 作为一个补充，即补充括号不足的部分。
     * 0: []
     * 1: [()]
     * 2: [()(), (())]
     * 3: [()()(), ()(()), (())(), (()()), ((()))]
     * 当 i = 3 时，j 取值 [0, 3)， x 取值从第 0 个结果集到第 2 个结果集，全部取一遍，x 被括号包围后即
     * "()", "(())", "(()())", "((()))"
     * 而这些括号都需要补充一定量的括号才能成为 3 个完整括号的一个结果。
     * y 此时的取值为 (3, 0]， 看到 y 的取值和 x 刚好相反，所以 y 的下标为 i - j - 1， 此时就可以将 x
     * 追加上 y 的值可形成 3 个括号的最终结果。
     * y 的取值为 "()()", "(())", "()", ""
     * 之前所有产生的结果加上一个括号即 x，并补充上缺少的括号数 y。
     * 初始化： dp[0][0] = "" 有 0 对括号的结果只有一种
     * 结果： dp[n]
     */
    public List<String> generateParenthesis3(int n) {
        List<List<String>> dp = new ArrayList<>();
        dp.add(Collections.singletonList(""));
        for (int i = 1; i <= n; ++i) {                  // 生成 n 对括号
            List<String> pars = new ArrayList<>();
            for (int j = 0; j < i; ++j)                 // [0, i)
                for (String x : dp.get(j))              // 0 1 2
                    for (String y : dp.get(i - j - 1))  // 2 1 0
                        pars.add("(" + x + ")" + y);
            
            dp.add(pars);
        }
        System.out.println(dp);
        return dp.get(n);
    }
    
}
