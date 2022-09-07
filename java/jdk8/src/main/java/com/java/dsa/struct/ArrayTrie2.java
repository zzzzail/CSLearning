package com.java.dsa.struct;

public class ArrayTrie2 {
    
    private final TrieNode root;
    
    public ArrayTrie2() {
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
            else cur = cur.get(c);
        }
        return cur;
    }
    
    static class TrieNode {
        // 也可以用 HashMap
        private final TrieNode[] nodes;
        private boolean end;
        
        public TrieNode() {
            this.nodes = new TrieNode[26];
            this.end = false;
        }
        
        public boolean contain(char c) {
            int index = c - 'a';
            if (0 <= index && index < 26)
                return this.nodes[index] != null;
            else
                return false;
        }
        
        public void set(char c) {
            int index = c - 'a';
            this.nodes[index] = new TrieNode();
        }
        
        public TrieNode get(char c) {
            if (contain(c)) return this.nodes[c - 'a'];
            else return null;
        }
        
        public boolean isEnd() {
            return end;
        }
        
        public void setEnd(boolean end) {
            this.end = end;
        }
    }
}
