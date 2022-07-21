package com.jdk17;

public class SealedClassDemo {
    
    public static void main(String[] args) {
    
    }
    
    static class A {
    
    }
    
    static sealed class B
            permits C {
    
    }
    
    /**
     * 继承自 sealed 类时，子类必须指定是否密封（final、non-sealed、sealed）
     * non-sealed： 不密封，任何子类都可以继承
     * sealed： 可以指定对哪些类密封
     * final： 任何子类都不能继承
     */
    static non-sealed class C extends B {
    
    }
    
}
