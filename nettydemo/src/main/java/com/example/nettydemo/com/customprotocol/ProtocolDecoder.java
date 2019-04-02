/*
 * Copyright @ 2000 Shanghai Hand Co. Ltd.
 * All right reserved.
 * @author: wenxin.ma@hand-china.com
 * date: 2019-02-17
 */
package com.example.nettydemo.com.customprotocol;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * 解码器
 *
 */
public class ProtocolDecoder extends ByteToMessageDecoder {

    private final static int HEADER_LENGTH = 44;// header的长度

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        // 长度不足，退出
        if (in.readableBytes() < HEADER_LENGTH) {
            return;
        }
        // 获取协议的版本
        int version = in.readInt();
        // 获取消息长度
        int contentLength = in.readInt();
        // 获取SessionId
        byte[] sessionByte = new byte[36];
        in.readBytes(sessionByte);
        String sessionId = new String(sessionByte);
        // 组装协议头
        ProtocolHeader header = new ProtocolHeader(version, contentLength, sessionId);

        // 长度不足重置读index，退出
        if (in.readableBytes() < contentLength) {
            in.setIndex(in.readerIndex() - HEADER_LENGTH, in.writerIndex());
            return;
        }

        byte[] content = new byte[contentLength];
        // 读取消息内容
        in.readBytes(content);

        ProtocolMessage message = new ProtocolMessage(header, new String(content));

        out.add(message);
    }
}
