package com.dsa.struct;

public class UnionFind {
    
    private int count;
    
    private int[] parent;
    
    public UnionFind(int n) {
        count = n;
        parent = new int[n];
        // 先是每一个元素都是自己为一组
        for (int i = 0; i < n; ++i) {
            parent[i] = i;
        }
    }
    
    public int find(int p) {
        // 如果要找的元素不是头元素，则向上查找
        while (p != parent[p]) {
            parent[p] = parent[parent[p]];
            p = parent[p];
        }
        return p;
    }
    
    public void union(int p, int q) {
        int rootP = find(p);
        int rootQ = find(q);
        if (rootP == rootQ) return;
        parent[rootP] = rootQ;
        --count;
    }
    
    public boolean isConnected(int p, int q) {
        int rootP = find(p);
        int rootQ = find(q);
        return rootP == rootQ;
    }
    
    public int count() {
        return count;
    }
}
