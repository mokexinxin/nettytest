/*
 * Copyright @ 2000 Shanghai Hand Co. Ltd.
 * All right reserved.
 * @author: wenxin.ma@hand-china.com
 * date: 2019-02-17
 */
package com.example.nettydemo.com.customprotocol;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author wenxin.ma@hand-china.com
 * @version 1.0
 * @name temp1
 * @description
 * @date 2019/2/17
 */
public class ServerHandler extends SimpleChannelInboundHandler<ProtocolMessage> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ProtocolMessage msg) throws Exception {
        // 简单地打印出server接收到的消息
        System.out.println("接收:"+msg);
    }
}
