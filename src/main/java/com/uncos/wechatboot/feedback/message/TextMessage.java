package com.uncos.wechatboot.feedback.message;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

/**
 * 文本消息
 * Created by xuwen on 2016/1/16.
 */
public class TextMessage extends IdMessage {

    private String content; // 文本消息内容

    @JacksonXmlProperty(localName = "Content")
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
