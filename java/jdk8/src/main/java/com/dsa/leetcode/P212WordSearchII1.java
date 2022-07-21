package com.dsa.leetcode;

import com.dsa.struct.Trie;

import java.util.ArrayList;
import java.util.List;

/**
 * @link https://leetcode-cn.com/problems/word-search-ii/
 * 单词搜索
 */
public class P212WordSearchII1 {
    
    public static void main(String[] args) {
    
    }
    
    /**
     * 1. 遍历 words，按照每个字母的顺序依次在 board 中找，如果找不到则快速结束，如果可以找到完整的单词
     * 则添加到结果集中。
     * 2. 使用 Trie 字典树，将所有 words 中的单词全部加入到字典树中，构建起一个快速查找的字典树数据结构。
     * 用递归的方式依次查找
     */
    Trie trie;
    int[] dx = new int[]{0, 1, 0, -1};
    int[] dy = new int[]{-1, 0, 1, 0};
    
    public List<String> findWords(char[][] board, String[] words) {
        int m = board.length;
        int n = board[0].length;
        List<String> res = new ArrayList<>();
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (trie.startsWith(board[i][j] + ""))
                    dfs(board, i, j, "", res);
            }
        }
        return res;
    }
    
    private void dfs(char[][] board, int row, int col, String path, List<String> res) {
        if (!inArea(board, row, col)) return;
        if (board[row][col] == '@') return;
        
        path = path + board[row][col];
        if (!trie.startsWith(path)) return;
        if (trie.search(path)) {
            res.add(path);
            return;
        }
        char tmp = board[row][col];
        board[row][col] = '@';
        for (int i = 0; i < 4; ++i) {
            dfs(board, row + dx[i], col + dy[i], path, res);
        }
        board[row][col] = tmp;
    }
    
    private boolean inArea(char[][] board, int row, int col) {
        return 0 <= row && row < board.length && 0 <= col && col < board[0].length;
    }
}
