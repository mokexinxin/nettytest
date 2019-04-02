/*
 * Copyright @ 2000 Shanghai Hand Co. Ltd.
 * All right reserved.
 * @author: wenxin.ma@hand-china.com
 * date: 2019-02-17
 */
package com.example.nettydemo.com.customprotocol;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

public class Server {

    // 指定端口号
    private static final int PORT = 8888;

    public static void main(String args[]) throws InterruptedException {

        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {

            ServerBootstrap serverBootstrap = new ServerBootstrap();
            // 指定socket的属性
            serverBootstrap.option(ChannelOption.SO_BACKLOG, 1024);
            serverBootstrap.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)  // 指定是一个NIO连接通道
                    .handler(new LoggingHandler(LogLevel.INFO))
                    .childHandler(new ChannelInitializer<SocketChannel>(){
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline pipeline = ch.pipeline();
                            //添加编码解码器
                            pipeline.addLast(new ProtocolEncoder());
                            pipeline.addLast(new ProtocolDecoder());
                            // 添加逻辑控制层
                            pipeline.addLast(new ServerHandler());

                        }
                    });

            // 绑定对应的端口号,并启动开始监听端口上的连接
            Channel ch = serverBootstrap.bind(PORT).sync().channel();

            System.out.printf("luck协议启动地址：127.0.0.1:%d/\n", PORT);

            // 等待关闭,同步端口
            ch.closeFuture().sync();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
