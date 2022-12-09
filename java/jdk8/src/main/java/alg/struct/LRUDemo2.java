package alg.struct;

import java.util.LinkedHashMap;
import java.util.Map;

public class LRUDemo2 {
    
    public static void main(String[] args) {
        LRUCache cache = new LRUCache(2);
        cache.put(1, 1);
        cache.put(2, 2);
        System.out.println(cache.get(1));
        cache.put(3, 3);
        System.out.println(cache.get(2));
        cache.put(4, 4);
        System.out.println(cache.get(1));
        System.out.println(cache.get(3));
        System.out.println(cache.get(4));
    }
    
    static class LRUCache {
        private final int capacity;
        private LinkedHashMap<Integer, Integer> map;
        
        public LRUCache(int capacity) {
            this.capacity = capacity;
            // accessOrder = true 按照访问顺序排序
            map = new LinkedHashMap<Integer, Integer>(capacity, 0.75f, true) {
                // 在 put 或 putAll 后，若改方法返回 true，则删除最老的节点(也就是链表中的第一个元素)
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
