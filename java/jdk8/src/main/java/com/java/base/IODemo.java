package com.java.base;

import java.io.*;

/**
 * @author zhangxq
 * @since 2022/9/25
 */
public class IODemo {
    
    public static void main(String[] args) {
        File file = new File("./a.txt");
        try (InputStream is = new FileInputStream(file)) {
            is.read();
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    
        try (OutputStream os = new FileOutputStream(file)) {
            os.write(1);
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
