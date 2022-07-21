package com.io.bio;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

/**
 * @author zail
 * @date 2022/6/3
 */
public class FileInputStreamDemo {
    
    public static void main(String[] args) throws IOException {
        File file = new File("./build.gradle");
        InputStream fis = new FileInputStream(file);
        // 将文件读入并显示
        // int b;
        // while ((b = fis.read()) != -1) {
        //     System.out.print((char) b);
        // }
        // 也可以直接读一个 byte 数组
        byte[] byteArr = new byte[10];
        int b;
        while ((b = fis.read(byteArr)) != -1) {
            for (byte b1 : byteArr) {
                System.out.print((char) b1);
            }
        }
        fis.close();
    }
}
