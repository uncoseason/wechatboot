package com.uncos.wechatboot.api.message.protocol;

import com.uncos.wechatboot.common.Message;
import com.uncos.wechatboot.common.MsgType;
import com.uncos.wechatboot.common.Voice;

/**
 * Created by xuwen on 2016/1/17.
 */
public class VoiceMessage extends Message {

    private Voice voice;

    public VoiceMessage() {
        this.msgType = MsgType.voice;
    }

    public Voice getVoice() {
        return voice;
    }

    public void setVoice(Voice voice) {
        this.voice = voice;
    }
}
