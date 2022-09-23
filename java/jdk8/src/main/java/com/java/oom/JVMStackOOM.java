package com.java.oom;

/**
 * VM Args：-Xss2M （这时候不妨设大些，请在32位系统下运行）
 *
 * @author zhangxq
 * @since 2022/9/23
 */
public class JVMStackOOM {
    
    private void dontStop() {
        while (true) {
        }
    }
    
    public void stackLeakByThread() {
        while (true) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    dontStop();
                }
            });
            thread.start();
        }
    }
    
    public static void main(String[] args) throws Throwable {
        JVMStackOOM oom = new JVMStackOOM();
        oom.stackLeakByThread();
    }
}
