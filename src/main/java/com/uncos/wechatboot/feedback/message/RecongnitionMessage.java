package com.uncos.wechatboot.feedback.message;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

/**
 * 开启了语音识别的语音消息
 * Created by xuwen on 2016/1/16.
 */
public class RecongnitionMessage extends VoiceMessage {

    private String recognition; // 语音识别结果，使用UTF8编码

    @JacksonXmlProperty(localName = "Recognition")
    public String getRecognition() {
        return recognition;
    }

    public void setRecognition(String recognition) {
        this.recognition = recognition;
    }
}
