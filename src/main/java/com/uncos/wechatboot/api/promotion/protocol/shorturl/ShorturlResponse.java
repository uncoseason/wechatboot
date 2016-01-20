package com.uncos.wechatboot.api.promotion.protocol.shorturl;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by xuwen on 2016/1/17.
 */
public class ShorturlResponse {

    private String shortUrl;

    @JsonProperty("short_url")
    public String getShortUrl() {
        return shortUrl;
    }

    public void setShortUrl(String shortUrl) {
        this.shortUrl = shortUrl;
    }
}
