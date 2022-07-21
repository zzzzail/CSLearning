package com.dsa.leetcode;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class P212WordSearchII2 {
    
    public List<String> findWords(char[][] board, String[] words) {
        // 构建字典树
        WordTrie myTrie = new WordTrie();
        TrieNode root = myTrie.root;
        for (String s : words) myTrie.insert(s);
        // 使用set防止重复
        Set<String> result = new HashSet<>();
        int m = board.length;
        int n = board[0].length;
        boolean[][] visited = new boolean[m][n];
        // 遍历整个二维数组
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                dfs(board, visited, i, j, m, n, result, root);
            }
        }
        System.out.print(result);
        return new LinkedList<>(result);
    }
    
    private void dfs(char[][] board, boolean[][] visited, int i, int j, int m, int n, Set<String> result, TrieNode cur) {
        // 边界以及是否已经访问判断
        if (i < 0 || i >= m || j < 0 || j >= n || visited[i][j])
            return;
        cur = cur.child[board[i][j] - 'a'];
        visited[i][j] = true;
        if (cur == null) {
            // 如果单词不匹配，回退
            visited[i][j] = false;
            return;
        }
        // 找到单词加入
        if (cur.isLeaf) {
            result.add(cur.val);
            // 找到单词后不能回退，因为可能是“ad” “addd”这样的单词得继续回溯
            // visited[i][j]=false;
            // return;
        }
        dfs(board, visited, i + 1, j, m, n, result, cur);
        dfs(board, visited, i, j + 1, m, n, result, cur);
        dfs(board, visited, i, j - 1, m, n, result, cur);
        dfs(board, visited, i - 1, j, m, n, result, cur);
        // 最后要回退，因为下一个起点可能会用到上一个起点的字符
        visited[i][j] = false;
    }
    
    //字典树
    static class WordTrie {
        public TrieNode root = new TrieNode();
        public void insert(String s) {
            TrieNode cur = root;
            for (char c : s.toCharArray()) {
                if (cur.child[c - 'a'] == null) {
                    cur.child[c - 'a'] = new TrieNode();
                    cur = cur.child[c - 'a'];
                }
                else
                    cur = cur.child[c - 'a'];
            }
            cur.isLeaf = true;
            cur.val = s;
        }
    }
    
    // 字典树结点
    static class TrieNode {
        public String val;
        public TrieNode[] child = new TrieNode[26];
        public boolean isLeaf = false;
        
        TrieNode() {
        }
    }
}
