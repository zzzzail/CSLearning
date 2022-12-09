package alg.leetcode;

import alg.struct.Trie;

import java.util.*;

/**
 * @link https://leetcode-cn.com/problems/word-search-ii/
 * 单词搜索
 */
public class P212WordSearchII {
    
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
    
    // 第二种解法
    public List<String> findWords2(char[][] board, String[] words) {
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
