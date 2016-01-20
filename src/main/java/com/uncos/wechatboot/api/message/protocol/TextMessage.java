package com.uncos.wechatboot.api.message.protocol;

import com.uncos.wechatboot.common.Message;
import com.uncos.wechatboot.common.MsgType;
import com.uncos.wechatboot.common.Text;

/**
 * Created by xuwen on 2016/1/17.
 */
public class TextMessage extends Message {

    private Text text;

    public TextMessage() {
        this.msgType = MsgType.text;
    }

    public Text getText() {
        return text;
    }

    public void setText(Text text) {
        this.text = text;
    }
}
