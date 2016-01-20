package com.uncos.wechatboot.api.oauth.protocol.refresh_access_token;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.uncos.wechatboot.api.base.protocol.access_token.AccessTokenResponse;

/**
 * Created by xuwen on 2016/1/16.
 */
public class RefreshAccessTokenResponse extends AccessTokenResponse{

    private String refreshToken;
    private String openid;
    private String scope;

    @JsonProperty("refresh_token")
    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }
}
