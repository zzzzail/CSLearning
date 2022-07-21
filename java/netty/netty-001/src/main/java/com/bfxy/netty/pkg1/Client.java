package com.bfxy.netty.pkg1;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.FixedLengthFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;

public class Client {
    
    public static void main(String[] args) throws Exception {
        
        // 线程组
        EventLoopGroup group = new NioEventLoopGroup();
        // 启动器
        Bootstrap b = new Bootstrap();
        b.group(group)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel sc) throws Exception {
                        // FixedLengthFrameDecoder 是处理定长消息体的组件
                        sc.pipeline().addLast(new FixedLengthFrameDecoder(5));
                        // StringDecoder 字符串解码器
                        sc.pipeline().addLast(new StringDecoder());
                        sc.pipeline().addLast(new ClientHandler());
                    }
                });
        
        ChannelFuture cf = b.connect("127.0.0.1", Server.PORT).sync();
        cf
                .channel()
                .writeAndFlush(Unpooled.wrappedBuffer("aaaaabbbbb".getBytes()));
        Thread.sleep(2000);
        
        cf
                .channel()
                .writeAndFlush(Unpooled.copiedBuffer("ccccccc   ".getBytes()));
        Thread.sleep(2000);
        
        cf
                .channel()
                .writeAndFlush(Unpooled.copiedBuffer("ccccccc".getBytes()));
        
        // 等待客户端端口关闭
        cf.channel().closeFuture().sync();
        group.shutdownGracefully();
    }
}
