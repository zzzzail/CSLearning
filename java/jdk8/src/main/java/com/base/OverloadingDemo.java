package com.base;

public class OverloadingDemo {
    
    public static void main(String[] args) {
        ofun(1);
        ofun(new Integer(100));
        Integer a = 1;
        ofun(a);
    }
    
    public static int ofun() {
        return 1;
    }
    
    public static int ofun(int a) {
        return 1;
    }
    
    public static int ofun(Integer a) {
        return 1;
    }
    
    public static int fun1(Integer... integers) {
        return integers[integers.length - 1];
    }
    
}


