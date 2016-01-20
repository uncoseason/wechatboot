package com.uncos.wechatboot.api.base.protocol.api_ticket;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by xuwen on 2016/1/20.
 */
public class ApiTicketResponse {

    private String ticket;
    private int expiresIn;

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    @JsonProperty("expires_in")
    public int getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(int expiresIn) {
        this.expiresIn = expiresIn;
    }
}
