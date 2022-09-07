package com.java.designpattern.simplefactory;

/**
 * 简单工厂模式示例
 *
 * 工厂模式的缺点：
 * 1. 增加客户端的复杂度（增加客户端使用的难度，也暴露了内部的部分实现）
 * 2. 不方便扩展子工厂
 *
 * @author zail
 * @date 2022/5/25
 */
public class Main {
    
    public static void main(String[] args) {
        test03();
    }
    
    private static void test01() {
        // 这段代码最大的问题是，Api 接口已知，还需要直到 ApiImpl 实现类
        Api api = new ApiImpl();
        api.test("这是一个测试！");
        
        // 有没有一种方法，实现仅直到 Api 即可，就能拿到 Api 接口下某个实例呢？
        // Api api = ApiFactory.get();
        // 上面这个段代码 Api 接口已知，ApiFactory 是 Api 接口的工厂类
        // 同样都是直到两个类有什么区别？
    }
    
    private static void test02() {
        /*
        简单工厂设计模式：
        提供一个创建对象实例的功能，而无须关心其具体实现。被创建实例的类型可以是接口、抽象类，也可以是具体的类。
        
        下面代码可以一些条件来获取相应的对象，其实这些条件可以更语义话一些的。
        所以在实际应用中应该将条件通过方法明明或增加额外参数的方式，使其更易于理解。
         */
        Api api1 = ApiFactory.createApi();
        api1.test("测试一下 api 1 呗");
        Api api2 = ApiFactory.createApi(2);
        api2.test("测试一下 api 2 呗");
    }
    
    private static void test03() {
        Api api = ApiFactory.createByProperties();
        api.test("测试一下配置文件配置的 api 实现。");
    }
    
}
