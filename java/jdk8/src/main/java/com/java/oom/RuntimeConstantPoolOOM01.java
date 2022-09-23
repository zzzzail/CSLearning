package com.java.oom;

import java.util.HashSet;
import java.util.Set;

/**
 * JDK 6 设置
 * VM Args：-XX:PermSize=6M -XX:MaxPermSize=6M
 *
 * JDK 8 设置
 * -XX:MaxMetaspaceSize=16m -Xms16m -Xmx16m
 *
 * @author zhangxq
 * @since 2022/9/23
 */
public class RuntimeConstantPoolOOM01 {
    
    public static void main(String[] args) {
        // 使用Set保持着常量池引用，避免Full GC回收常量池行为
        Set<String> set = new HashSet<>();
        // 在short范围内足以让6MB的PermSize产生OOM了
        short i = 0;
        while (true) {
            set.add(String.valueOf(i++).intern());
        }
    }
}
