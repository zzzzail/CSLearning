package com.java.dsa.shijiwenti;

/**
 * @author zhangxq
 * @since 2022/10/11
 */
public class LonLatUtilsTest {
    
    public static void main(String[] args) {
        // 11849398612/1141=10385099.572305
        long lon1 = LonLatUtils.lonConvert2m(118.49398612976076);
        System.out.println(lon1);
        // 3899497293/899=4337594.3192436
        long lat1 = LonLatUtils.latConvert2m(38.994972934024110);
        System.out.println(lat1);
    }
}
