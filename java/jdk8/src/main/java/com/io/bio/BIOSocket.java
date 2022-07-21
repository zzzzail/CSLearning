package com.io.bio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author zail
 * @date 2022/6/3
 */
public class BIOSocket {
    
    public static void main(String[] args) throws IOException {
        // 创建一个服务端 Socket 并监听 8090 端口
        ServerSocket ss = new ServerSocket(8090);
        System.out.println("执行第一步： new ServerSocket(8090)");
        while (true) {
            // 从服务端 Socket 中接收消息
            // 这一步是阻塞的
            Socket socket = ss.accept();
            System.out.println("执行第二步： ss.accept() 接收客户端发来的消息");
            
            // 重新启动一个线程去读数据
            new Thread(() -> {
                try {
                    InputStream is = socket.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(is));
                    while (true) {
                        System.out.println(reader.readLine());
                    }
                }
                catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        }
    }
}
