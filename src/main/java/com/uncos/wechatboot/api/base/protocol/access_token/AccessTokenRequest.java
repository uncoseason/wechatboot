package com.uncos.wechatboot.api.base.protocol.access_token;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.uncos.wechatboot.base.request.BaseAccountRequest;
import com.uncos.wechatboot.common.AccessTokenGrantType;

/**
 * Created by xuwen on 2016/1/14.
 */
public class AccessTokenRequest extends BaseAccountRequest {

    private AccessTokenGrantType grantType = AccessTokenGrantType.client_credential;

    @JsonProperty("grant_type")
    public AccessTokenGrantType getGrantType() {
        return grantType;
    }

    public void setGrantType(AccessTokenGrantType grantType) {
        this.grantType = grantType;
    }
}
