package com.java.asm;

/**
 * @author zhangxq
 * @since 2022/9/15
 */
public class EmployeeClassLoader extends ClassLoader {
    
    public Class defineClassFromClassFile(String className, byte[] classFile) throws ClassFormatError {
        return defineClass(className, classFile, 0, classFile.length);
    }
    
    public Class<?> defineClassForName(String name, byte[] data) {
        return this.defineClass(name, data, 0, data.length);
    }
}