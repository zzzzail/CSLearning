package com.base;

import java.math.BigDecimal;

/**
 * @author Zail
 * @version 1.0
 * @date 2022/5/21
 */
public class BigDecimalDemo {
    
    public static void main(String[] args) {
        BigDecimal a = new BigDecimal("100.000");
        System.out.println(a.scale());     // 小数点后有几位
        System.out.println(a.precision()); // 总数有几位
        
        a = new BigDecimal("100");
        System.out.println(a.scale());
        System.out.println(a.precision());
        
        // ROUND_DOWN
        BigDecimal b = new BigDecimal("2.225667").setScale(2, BigDecimal.ROUND_DOWN);
        System.out.println(b);// 2.22 直接去掉多余的位数
        
        // ROUND_UP
        BigDecimal c = new BigDecimal("2.224667").setScale(2, BigDecimal.ROUND_UP);
        System.out.println(c);//2.23 跟上面相反，进位处理
        
        // ROUND_CEILING
        // 天花板（向上），正数进位向上，负数舍位向上
        BigDecimal f = new BigDecimal("2.224667").setScale(2, BigDecimal.ROUND_CEILING);
        System.out.println(f);//2.23 如果是正数，相当于 BigDecimal.ROUND_UP
        BigDecimal g = new BigDecimal("-2.225667").setScale(2, BigDecimal.ROUND_CEILING);
        System.out.println(g);//-2.22 如果是负数，相当于 BigDecimal.ROUND_DOWN
        
        // ROUND_FLOOR
        // 地板（向下），正数舍位向下，负数进位向下
        BigDecimal h = new BigDecimal("2.225667").setScale(2, BigDecimal.ROUND_FLOOR);
        System.out.println(h);//2.22 如果是正数，相当于 BigDecimal.ROUND_DOWN
        BigDecimal i = new BigDecimal("-2.224667").setScale(2, BigDecimal.ROUND_FLOOR);
        System.out.println(i);//-2.23 如果是负数，相当于 BigDecimal.ROUND_HALF_UP
        
        // ROUND_HALF_UP
        BigDecimal d = new BigDecimal("2.225").setScale(2, BigDecimal.ROUND_HALF_UP);
        System.out.println("ROUND_HALF_UP" + d); //2.23 四舍五入（若舍弃部分 >=.5，就进位）
        
        // ROUND_HALF_DOWN
        BigDecimal e = new BigDecimal("2.225").setScale(2, BigDecimal.ROUND_HALF_DOWN);
        System.out.println("ROUND_HALF_DOWN" + e);//2.22 四舍五入（若舍弃部分 >.5, 就进位）
        
        // ROUND_HALF_EVEN
        // 具体见百度 银行家舍入法
        // ROUND_UNNECESSARY 如果小数丢失精度则报错
        BigDecimal l = new BigDecimal("2.215").setScale(4, BigDecimal.ROUND_UNNECESSARY);
        System.out.println(l); // 2.215
    }
    
}
