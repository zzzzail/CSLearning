package designpattern.flyweight;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhangxq
 * @since 2022/9/14
 */
public class FlyweightFactory {
    
    // 缓存多个 flyweight
    private Map<String, Flyweight> fsMap = new HashMap<>();
    
    public Flyweight getFlyweight(String key) {
        Flyweight f = fsMap.get(key);
        if (f == null) {
            f = new ConcreteFlyweight(key);
            fsMap.put(key, f);
        }
        return f;
    }
    
}
