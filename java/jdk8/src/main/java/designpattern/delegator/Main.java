package designpattern.delegator;

/**
 * @author zhangxq
 * @since 2022/9/19
 */
public class Main {
    
    public static void main(String[] args) {
        Component component = new Component();
        component.fun();
    
        System.out.println("-----------------");
    
        EnhanceComponent ec = new EnhanceComponent(component);
        ec.enhanceFun();
        
        /*
          MyBatis 中的 CachingExecutor 就是用的代理模式实现的一级缓存。
          在某个已配置的 Executor 上增加一层缓存的功能。
         */
    }
}
