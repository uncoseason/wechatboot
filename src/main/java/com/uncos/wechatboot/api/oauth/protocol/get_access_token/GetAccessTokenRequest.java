package com.uncos.wechatboot.api.oauth.protocol.get_access_token;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.uncos.wechatboot.base.request.BaseAccountRequest;
import com.uncos.wechatboot.common.OAuthGrantType;

/**
 * Created by xuwen on 2016/1/16.
 */
public class GetAccessTokenRequest extends BaseAccountRequest {

    private String code;
    private OAuthGrantType grantType = OAuthGrantType.authorization_code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @JsonProperty("grant_type")
    public OAuthGrantType getGrantType() {
        return grantType;
    }

    public void setGrantType(OAuthGrantType grantType) {
        this.grantType = grantType;
    }
}
