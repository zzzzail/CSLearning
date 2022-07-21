package com.designpattern.simplefactory;

/**
 * 客户自己实现的 Api 接口
 *
 * @author zail
 * @date 2022/5/26
 */
public class ApiImplByClient implements Api {
    
    @Override
    public void test(String str) {
        System.out.println("客户自己实现的 " + this.getClass().getName() + ": " + str);
    }
}
