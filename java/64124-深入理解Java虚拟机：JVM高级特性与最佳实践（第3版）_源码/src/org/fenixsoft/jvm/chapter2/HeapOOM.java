package org.fenixsoft.jvm.chapter2;

import java.util.ArrayList;
import java.util.List;

/**
 * Java 堆内存溢出
 * VM Args：-Xms20m -Xmx20m -XX:+HeapDumpOnOutOfMemoryError
 *
 * @author zzm
 */
public class HeapOOM {

    static class OOMObject {
    }

    public static void main(String[] args) {
        List<OOMObject> list = new ArrayList<OOMObject>();

        while (true) {
            list.add(new OOMObject());
        }
        
        List<char[]> caList = new ArrayList<>();
        // 准确一些的内存溢出
        // 一个 char 类型的数据占 1 个字节
        // char[] charArr = new char[1024]; // 占 1024 个字节，相当于 1KB
        // 10MB = 2 ^ 10 * 2 ^ 10
        // 我们直接循环 1024 次，每次申请一个 1KB 的数组即可
        for (int i = 0; i < 1024; i++) {
            caList.add(new char[1024]);
        }
    }
}

