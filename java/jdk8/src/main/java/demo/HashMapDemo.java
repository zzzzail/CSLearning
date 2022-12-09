package demo;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class HashMapDemo {
    
    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>();
        map.put("key1", "value1");
    
        String res1 = map.get("key1");
        System.out.println(res1);
    }
}
