package com.dsa.struct;

import java.util.ArrayList;
import java.util.List;

public class MGraph<T> {
    
    private List<List<T>> data;
    
    public MGraph(int n) {
        this.data = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            this.data.add(i, new ArrayList<>(n));
        }
    }
    
    public T get(int i, int j) {
        return data.get(i).get(j);
    }
    
    public List<List<T>> getData() {
        return data;
    }
    
    public void setData(List<List<T>> data) {
        this.data = data;
    }
    
    public void DFS() {
    
    }
}
