package designpattern.proxy.jdkproxy;

import java.lang.reflect.Proxy;

public class JdkProxyFactory {
    
    public static Object getProxy(Object target) {
        // 获取某个类的代理对象
        return Proxy.newProxyInstance(
                // 目标类的类加载
                target.getClass().getClassLoader(),
                // 代理需要实现的接口，可指定多个（问题：只能代理实现有接口的类）
                target.getClass().getInterfaces(),
                // 代理对象对应的自定义 InvocationHandler
                new DebugInvocationHandler(target)
        );
    }
    
    public static <T> T getProxyByClass(Class<T> clazz)
            throws InstantiationException, IllegalAccessException {
        T instance = clazz.newInstance();
        return (T) Proxy.newProxyInstance(
                instance.getClass().getClassLoader(),
                instance.getClass().getInterfaces(),
                new DebugInvocationHandler(instance)
        );
    }
    
}
