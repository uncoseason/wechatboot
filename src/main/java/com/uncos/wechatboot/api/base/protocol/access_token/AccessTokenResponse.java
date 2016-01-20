package com.uncos.wechatboot.api.base.protocol.access_token;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by xuwen on 2016/1/14.
 */
public class AccessTokenResponse {

    private String accessToken;
    private int expiresIn;

    @JsonProperty("access_token")
    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    @JsonProperty("expires_in")
    public int getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(int expiresIn) {
        this.expiresIn = expiresIn;
    }
}
