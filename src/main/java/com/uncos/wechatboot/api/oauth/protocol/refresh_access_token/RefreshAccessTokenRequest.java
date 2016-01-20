package com.uncos.wechatboot.api.oauth.protocol.refresh_access_token;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.uncos.wechatboot.base.request.BaseAppidRequest;
import com.uncos.wechatboot.common.OAuthGrantType;

/**
 * Created by xuwen on 2016/1/16.
 */
public class RefreshAccessTokenRequest extends BaseAppidRequest {


    private OAuthGrantType grantType = OAuthGrantType.refresh_token;
    private String refreshToken;

    @JsonProperty("grant_type")
    public OAuthGrantType getGrantType() {
        return grantType;
    }

    public void setGrantType(OAuthGrantType grantType) {
        this.grantType = grantType;
    }

    @JsonProperty("refresh_token")
    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
