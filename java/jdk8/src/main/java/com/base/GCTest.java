package com.base;

/**
 * 本实例用于 Java GC 以后，新生代 survivor 区域的变化，以及晋升到老年代的时间和方式的测试代码。需要自行分
 * 步注释不需要的代码进行反复测试对比。
 * 由于 Java 的 main 函数以及其他基础服务也会占用一些 Eden 空间，所以要提前空跑一次 main 函数，来看看这部分占用。
 * 自定义的代码中，我们使用堆内分配数组和栈内分配数组的方式来分别模拟不可被 GC 的和可被 GC 的资源。
 */
public class GCTest {
    
    public static void main(String[] args) {
        /*
        调试参数：
        ```
        -verbose:gc                         // 在控制台输出 GC 信息
        -Xms200M                            // 最小堆空间大小
        -Xmx200M                            // 最大堆空间大小
        -Xmn50M                             // 设置新生代堆大小
        -XX:MaxTenuringThreshold=3          // 晋升到老年代的年龄阈值
        -XX:TargetSurvivorRatio=60          // 目标存活率（默认 50%）
        -XX:+PrintGCDetails                 // 打印 GC 详细信息
        -XX:+PrintTenuringDistribution      // 打印
        -XX:+PrintGCDateStamps              // 打印 GC 时间戳
        -XX:+UseConcMarkSweepGC
        -XX:+UseParNewGC                    // 使用新 GC
        ```
        -XX:TargetSurvivorRatio=percent    // 幸存者空间百分比
        Sets the desired percentage of survivor space (0 to 100) used after young garbage collection. By default, this option is set to 50%.
        The following example shows how to set the target survivor space ratio to 30%:
          -XX:TargetSurvivorRatio=30
        空跑一次main函数来查看java服务本身占用的空间大小，我这里是占用了3M。所以 40-3=37，下面分配
        三个 1M 的数组和一个 34M 的垃圾数组。
        为了达到 TargetSurvivorRatio（期望占用的Survivor区域的大小）这个比例指定的值, 即
        5M * 60% = 3M(Desired survivor size)，这里用1M的数组的分配来达到 Desired survivor size。
        说明: 5M 为 S 区的 From 或 To 的大小，60% 为 TargetSurvivorRatio 参数指定,可以更改参数获
        取不同的效果。
         */
        byte[] byte1m_1 = new byte[1 * 1024 * 1024];
        byte[] byte1m_2 = new byte[1 * 1024 * 1024];
        byte[] byte1m_3 = new byte[1 * 1024 * 1024];
        // 使用函数方式来申请空间，函数运行完毕以后，就会变成垃圾等待回收。此时应保证eden的区域占用达到
        // 100%。可以通过调整传入值来达到效果。
        makeGarbage(34);
        // 再次申请一个数组，因为eden已经满了，所以这里会触发Minor GC
        byte[] byteArr = new byte[10*1024*1024];
        /*
        这次 Minor GC 时, 三个 1M 的数组因为尚有引用，所以进入 From 区域（因为是第一次 GC）age 为 1，且由于
        From 区已经占用达到了 60%(-XX:TargetSurvivorRatio=60)，所以会重新计算对象晋升的 age。
        计算方法见上文，计算出 age：min(age, MaxTenuringThreshold) = 1，输出中会有 Desired survivor
        size 3145728 bytes, new threshold 1 (max 3) 字样
        新的数组 byteArr 进入 Eden 区域。
        再次触发垃圾回收，证明三个 1M 的数组会因为其第二次回收后 age 为2，大于上一次计算出的 new threshold 1，
        所以进入老年代。而 byteArr 因为超过 survivor 的单个区域，直接进入了老年代。
         */
        makeGarbage(34);
        // System.out.println("结束");
    }
    
    private static void makeGarbage(int size){
        byte[] byteArrTemp = new byte[size * 1024 * 1024];
    }
}
