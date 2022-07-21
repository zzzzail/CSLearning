package com.dsa.struct;

public class UnionFind2 {
    
    private final int[] parent;
    
    private int count;
    
    public UnionFind2(int n) {
        this.parent = new int[n];
        this.count = n;
        for (int i = 0; i < n; i++) {
            this.parent[i] = i;
        }
    }
    
    public int find(int p) {
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
        return find(p) == find(q);
    }
    
    public int count() {
        return this.count;
    }
    
}
