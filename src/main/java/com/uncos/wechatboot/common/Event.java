package com.uncos.wechatboot.common;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.uncos.wechatboot.feedback.message.Message;

/**
 * 事件消息
 * Created by xuwen on 2016/1/16.
 */
public class Event extends Message {

    private EventType event; // 事件类型

    @JacksonXmlProperty(localName = "Event")
    public EventType getEvent() {
        return event;
    }

    public void setEvent(EventType event) {
        this.event = event;
    }
}
