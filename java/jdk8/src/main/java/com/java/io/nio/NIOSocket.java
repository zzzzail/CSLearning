package com.java.io.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.LinkedList;
import java.util.List;

/**
 * @author zail
 * @date 2022/6/3
 */
public class NIOSocket {
    
    public static void main(String[] args) throws IOException, InterruptedException {
        // 客户端
        List<SocketChannel> clients = new LinkedList<>();
        
        // 创建一个 ServerSocket
        ServerSocketChannel ss = ServerSocketChannel.open();
        // 绑定端口号
        ss.bind(new InetSocketAddress(8090));
        // 设置非阻塞，这里是 OS 的 NONBLOCK
        // 详细可查看 man 2 socket 中的 SOCK_NONBLOCK 配置
        ss.configureBlocking(false);
        
        while (true) {
            // 开始接收客户端的连接
            Thread.sleep(1000);
            // 不会阻塞，如果返回 null 说明未阻塞且没有建立连接
            SocketChannel client = ss.accept();
            // accept 调用内核了：1. 没有客户端连接进来，BIO 不返回，NIO 返回 null
            // 因为 NIO 没有阻塞住
            // 如果客户端连接进来，accept 返回这个客户端的 client object
            // NONBLOCKING 就是代码能往下走，只不过有不同的情况
            
            if (client == null) {
                System.out.println("null .....");
            }
            else {
                // 对客户端连接设置为非阻塞
                client.configureBlocking(false);
                int port = client.socket().getPort();
                System.out.println("client ... port: " + port);
                clients.add(client);
            }
            
            // 使用 buffer 的方式读每个客户端中的数据
            ByteBuffer buffer = ByteBuffer.allocateDirect(4096);
            for (SocketChannel c : clients) {
                int num = c.read(buffer); // 这里不会被阻塞住
                if (num > 0) {
                    buffer.flip();
                    byte[] aaa = new byte[buffer.limit()];
                    buffer.get(aaa);
                    String b = new String(aaa);
                    System.out.println(c.socket().getPort() + ": " + b);
                    // 清空 buffer
                    buffer.clear();
                }
            }
        }
    }
}
