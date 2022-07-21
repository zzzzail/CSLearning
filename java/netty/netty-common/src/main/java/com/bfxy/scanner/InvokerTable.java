package com.bfxy.scanner;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class InvokerTable {
    
    // 调用列表
    private static ConcurrentHashMap<String/* model */, Map<String/* cmd */, Invoker>> invokerTable
            = new ConcurrentHashMap<>();
    
    /**
     * $addInvoker
     *
     * @param module
     * @param cmd
     * @param invoker 执行器，可以通过执行器直接执行方法
     */
    public static void addInvoker(String module, String cmd, Invoker invoker) {
        Map<String, Invoker> map = invokerTable.get(module);
        if (map == null) {
            map = new HashMap<>();
            invokerTable.put(module, map);
        }
        map.put(cmd, invoker);
    }
    
    /**
     * $getInvoker
     *
     * @param module
     * @param cmd
     * @return
     */
    public static Invoker getInvoker(String module, String cmd) {
        Map<String, Invoker> map = invokerTable.get(module);
        if (map != null) {
            return map.get(cmd);
        }
        return null;
    }
    
}
