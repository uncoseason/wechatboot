package com.uncos.wechatboot.feedback.response;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.uncos.wechatboot.common.MsgType;
import com.uncos.wechatboot.common.Voice;

/**
 * 回复语音消息
 * Created by xuwen on 2016/1/17.
 */
public class VoiceResponse extends Response {

    private Voice voice;

    public VoiceResponse() {
        this.msgType = MsgType.voice;
    }

    @JacksonXmlProperty(localName = "Voice")
    public Voice getVoice() {
        return voice;
    }

    public void setVoice(Voice voice) {
        this.voice = voice;
    }

    @Override
    public MsgType getMsgType() {
        return MsgType.voice;
    }
}
