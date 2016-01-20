package com.uncos.wechatboot.common;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 公众号客户接口发给微信用户的消息
 * Created by xuwen on 2016/1/17.
 */
public class Message {

    protected String touser;
    protected MsgType msgType;

    public String getTouser() {
        return touser;
    }

    public void setTouser(String touser) {
        this.touser = touser;
    }

    @JsonProperty("msgtype")
    public MsgType getMsgType() {
        return msgType;
    }

    public void setMsgType(MsgType msgType) {
        this.msgType = msgType;
    }
}
