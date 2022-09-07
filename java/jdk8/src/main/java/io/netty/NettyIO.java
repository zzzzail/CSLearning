package io.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.pool.ChannelPool;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @author zail
 * @date 2022/6/3
 */
public class NettyIO {
    
    public static void main(String[] args) {
        // netty 中的时间循环组
        // 只有一个线程做 accept 接收
        NioEventLoopGroup boss = new NioEventLoopGroup(1);
        // 只做读写操作
        NioEventLoopGroup worker = new NioEventLoopGroup(5);
        
        ServerBootstrap bootstrap = new ServerBootstrap();
        try {
            bootstrap
                    .group(boss, worker)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(
                            new ChannelHandlerAdapter() {
                                @Override
                                public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
                                    ChannelPipeline p = ctx.pipeline();
                                    p.addLast(new MyInbound());
                                }
                            }
                    )
                    .bind(9090)
                    .sync()
                    .channel()
                    .closeFuture()
                    .sync();
            
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    static class MyInbound extends ChannelInboundHandlerAdapter {
    }
}
