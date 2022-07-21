package com.base;

/**
 * @author Zail
 * @version 1.0
 * @date 2022/5/23
 */
public class IntegerDemo {
    
    public static void main(String[] args) {
        Integer a = -2;
        Integer b = -2;
        System.out.println(a == b); // true 因为都在缓存中
        System.out.println(a.equals(b)); // true
        
        a = 299;
        b = 299;
        System.out.println(a == b); // false 因为不在缓存中
        System.out.println(a.equals(b)); // true
        
        Integer num1 = Integer.valueOf(20);
        Integer num2 = Integer.valueOf(20);
        System.out.println(num1 == num2); // true 在缓存中
        System.out.println(num1.equals(num2)); // true
        
        Integer num3 = Integer.valueOf(299);
        Integer num4 = Integer.valueOf(299);
        System.out.println(num3 == num4); // false 不在缓存中
        System.out.println(num3.equals(num4)); // true
        
        Integer num5 = new Integer(20);
        Integer num6 = new Integer(20);
        System.out.println(num5 == num6); // false 重新创建的对象不在缓存中
        System.out.println(num5.equals(num6)); // true
        
        Integer num7 = new Integer(299);
        Integer num8 = new Integer(299);
        System.out.println(num7 == num8); // false 不在缓存中
        System.out.println(num7.equals(num8)); // true
    }
}
