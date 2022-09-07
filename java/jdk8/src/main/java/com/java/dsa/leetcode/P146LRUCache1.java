package com.java.dsa.leetcode;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * LRU 缓存
 *
 * @link https://leetcode-cn.com/problems/lru-cache/
 */
public class P146LRUCache1 {
    
    public static void main(String[] args) {
    }
    
    static class LRUCache {
        private final int capacity;
        private LinkedHashMap<Integer, Integer> map;
        
        public LRUCache(int capacity) {
            this.capacity = capacity;
            map = new LinkedHashMap<Integer, Integer>(capacity, 0.75f, true) {
                @Override
                protected boolean removeEldestEntry(Map.Entry eldest) {
                    return size() > capacity;
                }
            };
        }
        
        public int get(int key) {
            return map.getOrDefault(key, -1);
        }
        
        public void put(int key, int value) {
            map.put(key, value);
        }
    }
    
}
