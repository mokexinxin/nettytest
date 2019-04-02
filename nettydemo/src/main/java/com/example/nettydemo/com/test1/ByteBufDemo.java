/*
 * Copyright @ 2000 Shanghai Hand Co. Ltd.
 * All right reserved.
 * @author: wenxin.ma@hand-china.com
 * date: 2019-02-26
 */
package com.example.nettydemo.com.test1;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

import java.nio.charset.Charset;

/**
 * @author wenxin.ma@hand-china.com
 * @version 1.0
 * @name ByteBufDemo
 * @description
 * @date 2019/2/26
 */
public class ByteBufDemo {

    public static void main(String[] args) {
        Charset utf8 = Charset.forName("UTF-8");
        ByteBuf buf = Unpooled.copiedBuffer("Netty in Action rocks!", utf8);
        ByteBuf slice = buf.slice(0, 14);
        ByteBuf copy = buf.copy(0, 14);
        System.out.println(buf.toString(utf8));
        System.out.println(slice.toString(utf8));
        System.out.println(copy.toString(utf8));

    }
}
