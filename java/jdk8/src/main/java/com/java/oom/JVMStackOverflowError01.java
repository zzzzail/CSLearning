package com.java.oom;

/**
 * @author zhangxq
 * @since 2022/9/23
 */
public class JVMStackOverflowError01 {
    
    /* 记录栈深度 */
    private int stackLength = 1;
    
    public void stackLeak() {
        stackLength++;
        stackLeak();
    }
    
    public static void main(String[] args) {
        /*
        -Xoss 设置本地方法栈大小
        -Xss 设置栈容量
        */
        JVMStackOverflowError01 oom = new JVMStackOverflowError01();
        try {
            oom.stackLeak();
        }
        catch (Throwable e) {
            System.out.println("stack length " + oom.stackLength);
            throw e;
        }
    }
}
