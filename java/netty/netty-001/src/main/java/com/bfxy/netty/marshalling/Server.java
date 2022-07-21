package com.bfxy.netty.marshalling;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class Server {
    
    public static void main(String[] args) throws InterruptedException {
        
        EventLoopGroup boss = new NioEventLoopGroup(1);
        EventLoopGroup work = new NioEventLoopGroup();
        
        // 链式调用的 builder
        ServerBootstrap sb = new ServerBootstrap();
        sb.group(boss, work)
                .channel(NioServerSocketChannel.class)
                .option(ChannelOption.SO_BACKLOG, 1024)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel sc) throws Exception {
                        // 设置 Marshalling 解码器
                        sc.pipeline().addLast(MarshallingCodeCFactory.buildMarshallingDecoder());
                        // 设置 Marshalling 编码器
                        sc.pipeline().addLast(MarshallingCodeCFactory.buildMarshallingEncoder());
                        sc.pipeline().addLast(new ServerHandler());
                    }
                });
        
        ChannelFuture cf = sb.bind(8765).sync();
        
        cf.channel().closeFuture().sync();
        boss.shutdownGracefully();
        work.shutdownGracefully();
    }
}
