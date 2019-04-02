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
//消息的主体
public class ProtocolMessage {

    private ProtocolHeader luckHeader;
    private String content;

    public ProtocolMessage(ProtocolHeader luckHeader, String content) {
        this.luckHeader = luckHeader;
        this.content = content;
    }

    public ProtocolHeader getLuckHeader() {
        return luckHeader;
    }

    public void setLuckHeader(ProtocolHeader luckHeader) {
        this.luckHeader = luckHeader;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return String.format("[version=%d,contentLength=%d,sessionId=%s,content=%s]", luckHeader.getVersion(),
                luckHeader.getContentLength(), luckHeader.getSessionId(), content);
    }
}
