package demo;

import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * @author zail
 * @date 2022/6/21
 */
public class AQSDemo {
    
    public static void main(String[] args) {
    
    }
    
    public static void unsafeTest() throws NoSuchFieldException {
        Unsafe unsafe = Unsafe.getUnsafe();
        // 获取 head 对象中成员变量在内存中的偏移量
        Field field = AbstractQueuedSynchronizer.class.getDeclaredField("head");
        long offset = unsafe.objectFieldOffset(field);
        System.out.println(offset);
    }
}
