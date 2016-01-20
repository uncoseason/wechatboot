package com.uncos.wechatboot.feedback.event;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

/**
 * 包含二维码的ticket的事件
 * Created by xuwen on 2016/1/16.
 */
public class TicketEvent extends KeyEvent {

    private String ticket; // 二维码的ticket，可用来换取二维码图片

    @JacksonXmlProperty(localName = "Ticket")
    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }
}
