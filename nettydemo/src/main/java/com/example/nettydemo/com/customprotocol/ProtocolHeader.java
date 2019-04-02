/*
 * Copyright @ 2000 Shanghai Hand Co. Ltd.
 * All right reserved.
 * @author: wenxin.ma@hand-china.com
 * date: 2019-02-17
 */
package com.example.nettydemo.com.customprotocol;

/**
 * @author wenxin.ma@hand-china.com
 * @version 1.0
 * @name temp
 * @description
 * @date 2019/2/17
 */
//消息的头部
public class ProtocolHeader {

    // 协议版本
    private int version;
    // 消息内容长度
    private int contentLength;
    // 服务名称
    private String sessionId;

    public ProtocolHeader(int version, int contentLength, String sessionId) {
        this.version = version;
        this.sessionId = sessionId;
        this.contentLength = contentLength;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public int getContentLength() {
        return contentLength;
    }

    public void setContentLength(int contentLength) {
        this.contentLength = contentLength;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }
}
