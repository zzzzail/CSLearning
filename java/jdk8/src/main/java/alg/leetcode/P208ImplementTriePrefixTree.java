package alg.leetcode;

public class P208ImplementTriePrefixTree {
    
    private TrieNode root;
    
    public P208ImplementTriePrefixTree() {
        this.root = new TrieNode();
    }
    
    public void insert(String word) {
        TrieNode cur = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (!cur.contain(c)) {
                cur.set(c);
            }
            cur = cur.get(c);
        }
        cur.setEnd(true);
    }
    
    public boolean startsWith(String prefix) {
        return searchTrieNode(prefix) != null;
    }
    
    public boolean search(String word) {
        TrieNode node = searchTrieNode(word);
        return node != null && node.isEnd();
    }
    
    public TrieNode searchTrieNode(String word) {
        TrieNode cur = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (!cur.contain(c)) return null;
            cur = cur.get(c);
        }
        return cur;
    }
    
    static class TrieNode {
        
        private TrieNode[] nodes;
        
        private boolean end;
        
        public TrieNode() {
            this.nodes = new TrieNode[26];
            this.end = false;
        }
        
        public boolean contain(char c) {
            int index = c - 'a';
            if (0 <= index && index <= 26) return nodes[index] != null;
            else return false;
        }
        
        public TrieNode get(char c) {
            if (contain(c)) return nodes[c - 'a'];
            else return null;
        }
        
        public void set(char c) {
            nodes[c - 'a'] = new TrieNode();
        }
        
        public boolean isEnd() {
            return end;
        }
        
        public void setEnd(boolean end) {
            this.end = end;
        }
    }
    
}
