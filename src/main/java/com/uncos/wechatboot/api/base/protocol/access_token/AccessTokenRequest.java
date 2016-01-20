package com.uncos.wechatboot.api.base.protocol.access_token;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.uncos.wechatboot.base.request.BaseAccountRequest;

/**
 * Created by xuwen on 2016/1/14.
 */
public class AccessTokenRequest extends BaseAccountRequest {

    private String grantType = "client_credential";

    @JsonProperty("grant_type")
    public String getGrantType() {
        return grantType;
    }

    public void setGrantType(String grantType) {
        this.grantType = grantType;
    }
}
