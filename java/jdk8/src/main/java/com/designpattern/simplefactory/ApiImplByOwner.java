package com.designpattern.simplefactory;

/**
 * @author zail
 * @date 2022/5/26
 */
public class ApiImplByOwner implements Api {
    
    @Override
    public void test(String str) {
        System.out.println("包的拥有者实现的 " + this.getClass().getName() + ": " + str);
    }
    
}
