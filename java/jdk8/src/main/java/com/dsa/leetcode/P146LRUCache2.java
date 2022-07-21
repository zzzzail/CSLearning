package com.dsa.leetcode;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * LRU 缓存
 *
 * @link https://leetcode-cn.com/problems/lru-cache/
 */
public class P146LRUCache2 {
    
    public static void main(String[] args) {
    }
    
    static class LRUCache {
    
        private int capacity;
    
        private LinkedHashMap<Integer, Integer> map;
        
        public LRUCache(int capacity) {
            this.capacity = capacity;
            this.map = new LinkedHashMap<Integer, Integer>(capacity, 0.75F, true) {
                @Override
                protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
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
