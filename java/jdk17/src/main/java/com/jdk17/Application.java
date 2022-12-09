package com.jdk17;

/**
 * @author zail
 */
public class Application {
    
    public static void main(String[] args) {
        feature01();
        feature02();
        feature03();
        
        var a = 100;
        // a = a ** 2;
        // a = a ^ 200;
    }
    
    public static void feature01() {
        var i = 100;
        var s = """
                Hello World!
                SELECT * FROM U \
                WHERE heart in ME AND id = %s;
                """
                .formatted(String.valueOf(i));
        System.out.println(s);
    }
    
    public record Person(String name, int age) {
        public static String address;
        
        public String getName() {
            return name;
        }
    }
    
    public static void feature02() {
        Person person = new Person("Zail", 100);
        System.out.println(person);
        System.out.println(person.name);
        System.out.println(person.age);
        System.out.println(person.getName());
    }
    
    public static void feature03() {
        int week = 6;
        // 新 switch 写法
        String weekName = switch (week) {
            case 1 -> "星期一";
            case 2 -> "星期二";
            case 3 -> "星期三";
            case 4 -> "星期四";
            case 5 -> "星期五";
            case 6, 7 -> "休息日";
            default -> "星期八";
        };
        System.out.println(weekName);
    }
    
    // 密封类
    public static sealed class Person2 permits Employee, Manager {
    
    }
    
    public static final class Employee extends Person2 {
    
    }
    
    public static final class Manager extends Person2 {
    
    }
    
    public static void feature04(Object obj) {
        // 新instanceof表达式
        if (obj instanceof Person person) {
            System.out.println(person.name);
        }
    }
    
}
