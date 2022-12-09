package designpattern.singleton;

/**
 * 当 getInstance 方法第一次被调用的时候，它第一次读取 SingletonHolder.instance，导
 * 致 SingletonHolder 类得到初始化；
 * <p>
 * 而这个类在加载并初始化的时候，会初始化它的静态域，从而创建 Singleton 实例，由于静态的域，
 * 因此只会在虚拟机装载类的时候初始化一次，并由虚拟机来保证它的线程安全。
 *
 * @author zail
 * @date 2022/5/26
 */
public class Singleton04 {
    
    private Singleton04() {
    }
    
    public static Singleton04 getInstance() {
        return SingletonHolder.instance;
    }
    
    /**
     * 单例的内部类，也就是静态成员内部类，该内部类的实例与外部类的实例
     * 没有绑定关系，而且只有被调用的时候才会装载，从而实现了延迟加载
     */
    private static class SingletonHolder {
        
        /**
         * 静态初始化，由 JVM 的类加载器来保证其线程安全
         */
        private static Singleton04 instance = new Singleton04();
        
    }
    
}
