package com.java.designpattern.memento;

/**
 * @author zhangxq
 * @since 2022/9/14
 */
public class Main {
    
    public static void main(String[] args) {
        Originator o = new Originator();
        o.phase1();
    
        Originator phase1 = o.get(0);
        phase1.schema1();
    
        phase1 = o.get(0);
        phase1.schema2();
    }
}
