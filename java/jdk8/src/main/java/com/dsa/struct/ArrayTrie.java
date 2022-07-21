package com.dsa.struct;

public class ArrayTrie {
    
    private final TrieNode root;
    
    public ArrayTrie() {
        this.root = new TrieNode();
    }
    
    public void insert(String word) {
        TrieNode cur = root;
        for (int i = 0; i < word.length(); ++i) {
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
        for (int i = 0; i < word.length(); ++i) {
            char c = word.charAt(i);
            if (!cur.contain(c)) return null;
            cur = cur.get(c);
        }
        return cur;
    }
    
    static class TrieNode {
        private final TrieNode[] child;
        private boolean end;
        
        public TrieNode() {
            this.child = new TrieNode[26];
            this.end = false;
        }
        
        public boolean contain(char c) {
            int index = c - 'a';
            if (0 <= index && index < 26) {
                return child[index] != null;
            }
            else return false;
        }
        
        public TrieNode get(char c) {
            int index = c - 'a';
            if (contain(c)) return child[index];
            else return null;
        }
        
        public void set(char c) {
            int index = c - 'a';
            this.child[index] = new TrieNode();
        }
        
        public boolean isEnd() {
            return end;
        }
        
        public void setEnd(boolean end) {
            this.end = end;
        }
    }
    
}
