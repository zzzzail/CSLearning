package com.dsa.struct;

public class Recursion {
    
    public static void recursion(Integer level, Object... params) {
        // recursion terminator 递归结束条件
        if (level > Integer.MAX_VALUE) {
            return;
        }
        
        // process logic in current level 递归的逻辑处理
        process(level, params);
        
        // drill down 递归调用
        recursion(level + 1, params);
        
        // reverse the current level status if needed 清理当前层
    }
    
    private static void process(Integer level, Object[] params) {
    
    }
}
