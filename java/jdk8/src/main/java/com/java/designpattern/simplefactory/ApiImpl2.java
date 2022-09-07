package com.java.designpattern.simplefactory;

/**
 * @author zail
 * @date 2022/5/25
 */
public class ApiImpl2 implements Api {
    @Override
    public void test(String str) {
        System.out.println(this.getClass().getName() + ": " + str);
    }
}
