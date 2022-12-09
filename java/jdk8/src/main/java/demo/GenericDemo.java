package demo;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class GenericDemo {
    
    public static void main(String[] args) {
        String[] strs = {"x1", "x2"};
        printAll(strs);
        
        keyAndValue("key", "Value");
        
        erasureGenericTypeTest();
    }
    
    static class A<T> {
        T t;
        
        public A(T t) {
            this.t = t;
        }
    }
    
    public static <T> void printAll(T[] ts) {
        for (T t : ts) {
            System.out.print(t.toString() + ", ");
        }
        System.out.println();
    }
    
    public static <K, V> void keyAndValue(K key, V value) {
        System.out.println("Key = " + key + "; Value = " + value);
    }
    
    private static void erasureGenericTypeTest() {
        List list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();
        List<String> list3 = new ArrayList<>();
        try {
            // 用反射的方式强行往里插入数据是可行的
            List.class.getMethod("add", Object.class).invoke(list1, 1);
            List.class.getMethod("add", Object.class).invoke(list2, 1);
            List.class.getMethod("add", Object.class).invoke(list3, 1);
        }
        catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }
        
        // 这里都是 Integer 类型的
        Object res1 = list1.get(0);
        System.out.println(res1 + "是" + res1.getClass().getTypeName());
        Object res2 = list2.get(0);
        System.out.println(res1 + "是" + res2.getClass().getTypeName());
        Object res3 = list3.get(0);
        System.out.println(res1 + "是" + res3.getClass().getTypeName());
        /*
        结论：范型擦除是有一定风险的，这个风险就是可以通过反射的方式将其插入不是相应类型的值！
         */
        
        // 如果加入 extends 也不能保证其插入的安全性啊
        List<? extends Integer> list4 = new ArrayList<>();
        try {
            List.class.getMethod("add", Object.class).invoke(list4, "aaa");
        }
        catch (InvocationTargetException | IllegalAccessException | NoSuchMethodException e) {
            e.printStackTrace();
        }
        Object res4 = list4.get(0);
        System.out.println(res4.getClass().getTypeName());
        // res4 = (Integer) list4.get(0); // cast 失败
        
        // 向上转型是安全的，可以添加 B 的子类对象
        // <B super C> B 是 C 的父类
        List<? super C> list5 = new ArrayList<B>();
        // list5.add(new B()); // 报错
        list5.add(new C());
        
        // 向下转型是不安全的 （C extends B）C 是 B 的子类
        // List<? extends B> list6 = new ArrayList<C>(); // 不会报错
        List<? extends B> list6 = new ArrayList<C>();
        // list6.add(new B()); // 在 add 的时候会出现编译错误
    
        // 编译错误，如果编译成功的话，就可以往 list7 中添加其他 B 的子类对象了
        // List<B> list7 = new ArrayList<C>();
        // List<Object> list7 = new ArrayList<C>();
        List<? extends B> list8 = new ArrayList<C>();
        // list8.add(new C()); // 编译错误
    }
    
    static class B {
    }
    
    static class C extends B {
    }
}
