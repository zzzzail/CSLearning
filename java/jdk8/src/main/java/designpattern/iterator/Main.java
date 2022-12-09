package designpattern.iterator;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhangxq
 * @since 2022/9/13
 */
public class Main {
    
    public static void main(String[] args) {
        User u1 = new User("A", "xxx");
        User u2 = new User("B", "xxx");
        User u3 = new User("C", "xxx");
        
        List<User> list = new ArrayList<>();
        list.add(u1);
        list.add(u2);
        list.add(u3);
    
        String str = "xxx";
        str.replace("x", "A");
        
        // 返回这个迭代器即可
        Iterator<User> iterator = new Iterator<>(list);
        while (iterator.hasNext()) {
            User u = iterator.next();
            System.out.println(u.getUsername());
        }
    }
}
