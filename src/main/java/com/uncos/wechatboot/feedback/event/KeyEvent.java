package com.uncos.wechatboot.feedback.event;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.uncos.wechatboot.common.Event;

/**
 * 包含事件KEY值的事件
 * Created by xuwen on 2016/1/16.
 */
public class KeyEvent extends Event {

    private String eventKey; // 事件KEY值

    @JacksonXmlProperty(localName = "EventKey")
    public String getEventKey() {
        return eventKey;
    }

    public void setEventKey(String eventKey) {
        this.eventKey = eventKey;
    }
}
