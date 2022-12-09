package demo.oom;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhangxq
 * @since 2022/9/23
 */
public class HeapOOM {
    
    public static void main(String[] args) {
        // -Xms20m 堆最小内存
        // -Xmx20m 堆最大内存 最小内存和最大内存设置相同时可避免自动堆扩展
        // -XX:+HeapDumpOnOutOfMemoryError 内存溢出时，自动 dump 下内存快找
        // -Xms20m -Xmx20m -XX:+HeapDumpOnOutOfMemoryError
        List<char[]> caList = new ArrayList<>();
        // 准确一些的内存溢出
        // 一个 char 类型的数据占 1 个字节
        // char[] charArr = new char[1024]; // 占 1024 个字节，相当于 1KB
        // 10MB = 2 ^ 10 * 2 ^ 10
        // 我们直接循环 1024 次，每次申请一个 1KB 的数组即可
        for (int i = 0; i < ( 2 << 20 ); i++) {
            System.out.println(i);
            caList.add(new char[1024]);
        }
    }
}
