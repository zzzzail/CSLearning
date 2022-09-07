package com.java.base;

import java.io.*;
import java.util.Scanner;

public class ResourceDemo {
    
    public static void main(String[] args) throws ClassNotFoundException {
        // 这种是 try-with-resources 自动关闭资源的方式
        try (
                Scanner scanner = new Scanner(new File("./zail.txt"))
        ) {
            while (scanner.hasNext()) {
                System.out.println(scanner.nextLine());
            }
        }
        catch (FileNotFoundException e) {
        
        }
    
        try (
                BufferedInputStream bin = new BufferedInputStream(new FileInputStream(new File("test.txt")));
                BufferedOutputStream bout = new BufferedOutputStream(new FileOutputStream(new File("out.txt")))
        ) {
            int b;
            while ((b = bin.read()) != -1) {
                bout.write(b);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    
        // 1. 通过类名获取 class
        Class<Target> clazz = Target.class;
        // 2. 通过 Class.forName 方法获取 class
        Class<?> clazz2 = Class.forName("cn.javaguide.TargetObject");
        // 3. 通过实例获取 class
        Target target = new Target();
        Class<? extends Target> clazz3 = target.getClass();
        // 4. 通过 ClassLoader class 加载器获取 class
        Class<?> clazz4 = ClassLoader.getSystemClassLoader().loadClass("cn.javaguide.TargetObject");
    }
    
    static class Target {
    
    }
}
