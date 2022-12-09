package designpattern.iterator;

import java.util.List;

/**
 * @author zhangxq
 * @since 2022/9/13
 */
public class Iterator<T> {
    
    int next;
    
    private List<T> list;
    
    public Iterator(List<T> list) {
        this.list = list;
        this.next = 0;
    }
    
    public boolean hasNext() {
        return next < list.size();
    }
    
    public T next() {
        return list.get(next++);
    }
}
