package com.uncos.wechatboot.feedback.message;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

/**
 * 语音消息
 * Created by xuwen on 2016/1/16.
 */
public class VoiceMessage extends MediaMessage {

    private String format; // 语音格式，如amr，speex等

    @JacksonXmlProperty(localName = "Format")
    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }
}
