package designpattern.proxy.cglibproxy;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author zail
 * @date 2022/5/25
 */
public class DebugMethodInterceptor implements MethodInterceptor {
    
    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        // 调用方法之前，我们可以添加自己的操作
        System.out.println("before method " + method.getName());
        Object result = proxy.invokeSuper(obj, args);
        // 调用方法之后，我们同样可以添加自己的操作
        System.out.println("after method " + method.getName());
        return result;
    }
    
}
