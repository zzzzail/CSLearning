package demo;

/**
 * @author zhangxq
 * @since 2022/9/20
 */
public class StaticInnerFun {
    
    static {
        // 类被加载的时候就执行
        System.out.println("StaticInnerFun 的静态代码块执行");
    }
    
    public static void main(String[] args) {
        System.out.println("1");
    }
}
