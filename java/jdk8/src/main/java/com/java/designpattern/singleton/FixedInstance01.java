package com.java.designpattern.singleton;

/**
 * 实现固定数量的实例
 *
 * @author zail
 * @date 2022/5/26
 */
public class FixedInstance01 {
    
    private static int DEFAULT_COUNT = 3;
    private static FixedInstance01[] instances;
    // 记录取哪个实例
    private static int round = 0;
    
    private FixedInstance01() {
    }
    
    public static FixedInstance01 getInstance() {
        int n = DEFAULT_COUNT;
        if (instances == null) {
            initializeInstances(n); // 初始化实例数组
        }
        if (instances[round] == null) {
            initializeInstance(round);
        }
        FixedInstance01 res = instances[round];
        round = (round + 1) % n;
        return res;
    }
    
    private static void initializeInstances(int n) {
        synchronized (FixedInstance01.class) {
            if (instances == null) {
                instances = new FixedInstance01[n];
            }
        }
    }
    
    private static void initializeInstance(int idx) {
        synchronized (instances) {
            instances[idx] = new FixedInstance01();
        }
    }
}
