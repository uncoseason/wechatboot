package com.uncos.wechatboot.feedback.message;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

/**
 * 包含消息id的消息
 * Created by xuwen on 2016/1/16.
 */
public class IdMessage extends Message{

    protected String msgId; // 消息id，64位整型

    @JacksonXmlProperty(localName = "MsgId")
    public String getMsgId() {
        return msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }
}
