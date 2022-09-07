package com.java.io.bio;

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
public class Server {
    
    public static void main(String[] args) throws IOException {
        System.out.println("服务器启动...");
        // 定义一个服务端 Socket 并监听 9999 端口
        ServerSocket ss = new ServerSocket(9999);
        // 监听客户端发来的 socket 请求
        Socket socket = ss.accept();
        
        // 从客户端 socket 中获取到输入流
        InputStream is = socket.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        String msg;
        while ((msg = br.readLine()) != null) {
            System.out.println("服务端收到消息：" + msg);
        }
    }
}
