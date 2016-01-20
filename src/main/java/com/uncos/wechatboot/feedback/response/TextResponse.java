package com.uncos.wechatboot.feedback.response;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlCData;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.uncos.wechatboot.common.MsgType;

/**
 * 回复文本消息
 * Created by xuwen on 2016/1/17.
 */
public class TextResponse extends Response {

    private String content; // 文本消息内容

    public TextResponse() {
        this.msgType = MsgType.text;
    }

    @JacksonXmlProperty(localName = "Content")
    @JacksonXmlCData
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
