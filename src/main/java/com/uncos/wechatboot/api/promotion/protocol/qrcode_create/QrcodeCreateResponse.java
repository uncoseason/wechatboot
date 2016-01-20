package com.uncos.wechatboot.api.promotion.protocol.qrcode_create;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by xuwen on 2016/1/17.
 */
public class QrcodeCreateResponse {

    private String ticket;
    private int expireSeconds;
    private String url;

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    @JsonProperty("expire_seconds")
    public int getExpireSeconds() {
        return expireSeconds;
    }

    public void setExpireSeconds(int expireSeconds) {
        this.expireSeconds = expireSeconds;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
