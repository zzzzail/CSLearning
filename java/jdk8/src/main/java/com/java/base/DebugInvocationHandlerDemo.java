package com.java.base;

import java.lang.reflect.Method;

public class DebugInvocationHandlerDemo {
    
    public static void main(String[] args) throws Throwable {
        String[] strs = {"sss", "bbb", };
        Bug bug = new Bug();
        DebugInvocationHandler handler = new DebugInvocationHandler(bug);
        Class<Bug> clazz = Bug.class;
        Method method = null;
        try {
            method = clazz.getDeclaredMethod("print");
            method.setAccessible(true); // 设置即使是private也可以访问
        }
        catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        handler.invoke(bug, method, null);
    }
    
    static class Bug {
        private int print() {
            System.out.println("print it");
            return 1;
        }
    }
    
}
