package com.uncos.wechatboot.api.promotion.protocol.shorturl;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.uncos.wechatboot.base.request.BaseAccessTokenRequest;

/**
 * Created by xuwen on 2016/1/17.
 */
public class ShorturlRequest extends BaseAccessTokenRequest {

    private String action = "long2short";
    private String longUrl;

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    @JsonProperty("long_url")
    public String getLongUrl() {
        return longUrl;
    }

    public void setLongUrl(String longUrl) {
        this.longUrl = longUrl;
    }
}
