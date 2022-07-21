package com.designpattern.singleton;

/**
 * @author zail
 * @date 2022/5/26
 */
public class Main {
    
    public static void main(String[] args) {
        // test05();
        test06();
    }
    
    public static void test05() {
        Singleton05 instance = Singleton05.UNIQUE_INSTANCE;
        instance.singletonOperation();
    }
    
    public static void test06() {
        FixedInstance01 instance1 = FixedInstance01.getInstance();
        System.out.println(instance1);
        FixedInstance01 instance2 = FixedInstance01.getInstance();
        System.out.println(instance2);
        FixedInstance01 instance3 = FixedInstance01.getInstance();
        System.out.println(instance3);
        FixedInstance01 instance4 = FixedInstance01.getInstance();
        System.out.println(instance4);
        FixedInstance01 instance5 = FixedInstance01.getInstance();
        System.out.println(instance5);
        FixedInstance01 instance6 = FixedInstance01.getInstance();
        System.out.println(instance6);
    }
}
