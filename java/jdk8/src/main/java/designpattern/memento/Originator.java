package designpattern.memento;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhangxq
 * @since 2022/9/14
 */
public class Originator implements Cloneable {
    
    private final OriginatorMemento memento = new OriginatorMemento();
    
    private String name;
    
    private Integer num;
    
    Originator get(int phase) {
        return memento.retrieve(phase);
    }
    
    // 运行的第一阶段
    public void phase1() {
        this.name = "phase1";
        this.num = 100;
        memento.save(this);
    }
    
    // 方案 1
    public void schema1() {
        this.name += ",schema1";
        this.num += 100;
        System.out.println(this.name + ", " + this.num);
    }
    
    // 方案 2
    public void schema2() {
        this.name += ",schema2";
        this.num += 100;
        System.out.println(this.name + ", " + this.num);
    }
    
    @Override
    public Originator clone() {
        try {
            Originator clone = (Originator) super.clone();
            clone.name = this.name;
            clone.num = this.num;
            return clone;
        }
        catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
    
    static class OriginatorMemento implements Memento<Originator> {
        
        private final List<Originator> mementos;
        
        public OriginatorMemento() {
            this.mementos = new ArrayList<>();
        }
        
        @Override
        public List<Originator> mementos() {
            return this.mementos;
        }
        
        @Override
        public boolean save(Originator originator) {
            return mementos.add(originator);
        }
        
        @Override
        public Originator retrieve(int phase) {
            Originator o = mementos.get(phase);
            return o.clone();
        }
        
        @Override
        public boolean remove(Originator originator) {
            return mementos.remove(originator);
        }
        
        @Override
        public boolean remove(int phase) {
            Originator o = mementos.remove(phase);
            return o != null;
        }
    }
    
}
