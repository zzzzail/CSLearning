package designpattern.facade;

/**
 * 外观设计模式
 *
 * 为子系统的一组接口提供一个一致的界面，Facade 模式定义了一个高层接口，这个接口使得这一子系统更加容易使用。
 *
 * @author zail
 * @date 2022/5/26
 */
public class Main {
    
    public static void main(String[] args) {
        test01();
        System.out.println("-----------------");
        test02();
    }
    
    /**
     * 问题 1：客户端生成代码功能，需要与生成代码子系统内部的多个模块交互（new 多个子系统内部的对象）。
     * 不能让客户端简单的生成代码功能，且内部某个模块发生变化，还可能引起客户端也要随着发生变化。
     */
    public static void test01() {
        new PresentationGenerator().generate();
        new BusinessGenerator().generate();
        new DAOGenerator().generate();
    }
    
    public static void test02() {
        GeneratorFacade facade = new GeneratorFacade();
        facade.gen(); // 使用外观模式生成
    }
}
