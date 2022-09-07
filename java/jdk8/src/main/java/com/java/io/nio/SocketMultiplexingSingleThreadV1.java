package com.java.io.nio;

import jdk.jfr.events.SocketReadEvent;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * @author zail
 * @date 2022/6/3
 */
public class SocketMultiplexingSingleThreadV1 {
    
    private ServerSocketChannel server = null;
    private Selector selector = null;
    int port = 9090;
    
    public void initServer() {
        try {
            server = ServerSocketChannel.open();
            server.configureBlocking(false);
            server.bind(new InetSocketAddress(port));
            
            // 如果在 epoll 模型下，open -> epoll_create -> fd3
            // select、poll、epoll 优先选择 epoll，但是可以通过 -D 修正
            selector = Selector.open();
            
            // server 相当与进入 listen 状态的 fd4
            /*
             register 注册
             如果是 select、poll： jvm 里开辟一个数组，将 fd4 放进去
             如果是 epoll： epoll_ctl(fd3, ADD, fd4, EPOLLIN)
             */
            server.register(selector, SelectionKey.OP_ACCEPT);
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    
    public void start() {
        initServer();
        System.out.println("服务器启动了。。。");
        try {
            while (true) {
                Set<SelectionKey> keys = selector.keys();
                System.out.println("epoll size：" + keys.size());
    
                /*
                 select()：
                 1. select、poll：内核 select(fd4)、poll(fd4)
                 2. epoll： 内核的 epoll_wait()
                 selector.wakeup() 结果返回 0
                 */
                while (selector.select(500) > 0) {
                    // 返回有状态的 fd 集合
                    Set<SelectionKey> selectedKeys = selector.selectedKeys();
                    Iterator<SelectionKey> iter = selectedKeys.iterator();
                    while (iter.hasNext()) {
                        SelectionKey key = iter.next();
                        iter.remove(); // 不移除的话会重复循环处理
                        // 判断是否可接收
                        if (key.isAcceptable()) {
                            // select、poll：内核没有分配内存空间，在 jvm 中保存
                            // epoll：通过 epoll_ctl 把新的客户端注册到内核空间
                            acceptHandler(key);
                        }
                        else if (key.isReadable()) {
                            readHandler(key);
                        }
                    }
                }
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private void acceptHandler(SelectionKey key) {
        try {
            ServerSocketChannel ss = (ServerSocketChannel) key.channel();
            SocketChannel client = ss.accept();
            client.configureBlocking(false);
            
            // 分配 8192 个字节缓冲区
            ByteBuffer buffer = ByteBuffer.allocateDirect(8192);
            /*
             select、poll：jvm 里开辟一个数组 fd7 放进去
             epoll：epoll_ctl(fd3, ADD, fd7, EPOLLIN)
             */
            client.register(selector, SelectionKey.OP_READ, buffer);
            System.out.println("--------------------------------");
            System.out.println("新客户端：" + client.getRemoteAddress());
            System.out.println("--------------------------------");
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    
    private void readHandler(SelectionKey key) {
        SocketChannel client = (SocketChannel) key.channel();
        ByteBuffer buffer = (ByteBuffer) key.attachment();
        
        buffer.clear();
        int read = 0;
        try {
            while (true) {
            
            }
        }
        finally {
        
        }
    }
}
