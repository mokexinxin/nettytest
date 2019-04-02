/*
 * Copyright @ 2000 Shanghai Hand Co. Ltd.
 * All right reserved.
 * @author: wenxin.ma@hand-china.com
 * date: 2019-02-17
 */
package com.example.nettydemo.com.customprotocol;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.util.UUID;

/**
 * @author wenxin.ma@hand-china.com
 * @version 1.0
 * @description
 * @date 2019/2/17
 */
public class Client {

    public static void main(String args[]) throws InterruptedException {

        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap();
            b.group(group).channel(NioSocketChannel.class).handler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel ch) throws Exception {
                    ChannelPipeline pipeline = ch.pipeline();
                    // 添加编码器
                    pipeline.addLast(new ProtocolEncoder());
                    // 添加解码器
                    pipeline.addLast(new ProtocolEncoder());
                    // 业务处理类（只打印了消息内容）
                    pipeline.addLast(new ClientHandler());
                }
            });

            // 连接服务端
            Channel ch = b.connect("127.0.0.1", 8888).sync().channel();
            int version = 1;
            String sessionId = UUID.randomUUID().toString();
            String str = "Hello!";

            // 发送1000000条消息
            for (int i = 0; i < 1000000; i++) {
                String content = str + "----" + i;
                ProtocolHeader header = new ProtocolHeader(version, content.length(), sessionId);
                ProtocolMessage message = new ProtocolMessage(header, content);
                ch.writeAndFlush(message);
            }

            ch.closeFuture().sync();

        } finally {
            group.shutdownGracefully();
        }
    }
}
