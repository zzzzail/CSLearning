package com.java.base;

import java.math.BigInteger;

/**
 * @author zail
 * @date 2022/7/5
 */
public class BigIntegerDemo {
    
    public static void main(String[] args) {
        // radix 基数
        BigInteger num1 = new BigInteger("20", 10);
        System.out.println(num1);
    }
}
