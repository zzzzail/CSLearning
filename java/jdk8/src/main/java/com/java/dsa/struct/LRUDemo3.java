package com.java.dsa.struct;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author zail
 * @date 2022/6/1
 */
public class LRUDemo3 {
    
    private int capacity;
    private Map<String, String> map;
    
    public LRUDemo3(int capacity) {
        this.capacity = capacity;
        // accessOrder = true 设置为有序访问
        this.map = new LinkedHashMap<String, String>(capacity, 0.75F, true) {
            @Override
            protected boolean removeEldestEntry(Map.Entry eldest) {
                return size() > capacity;
            }
        };
    }
    
    public void put(String key, String val) {
        map.put(key, val);
    }
    
    public String get(String key) {
        return map.getOrDefault(key, null);
    }
}
