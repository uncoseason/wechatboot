package com.uncos.wechatboot.api.oauth.protocol.redirect_uri;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.uncos.wechatboot.base.request.BaseAppidRequest;
import com.uncos.wechatboot.common.OAuthResponseType;
import com.uncos.wechatboot.common.OAuthScope;

/**
 * Created by xuwen on 2016/1/21.
 */
public class RedirectUriRequest extends BaseAppidRequest {

    private String redirectUri;
    private OAuthResponseType responseType = OAuthResponseType.code;
    private OAuthScope scope;
    private String state;

    @JsonProperty("redirect_uri")
    public String getRedirectUri() {
        return redirectUri;
    }

    public void setRedirectUri(String redirectUri) {
        this.redirectUri = redirectUri;
    }

    @JsonProperty("response_type")
    public OAuthResponseType getResponseType() {
        return responseType;
    }

    public void setResponseType(OAuthResponseType responseType) {
        this.responseType = responseType;
    }

    public OAuthScope getScope() {
        return scope;
    }

    public void setScope(OAuthScope scope) {
        this.scope = scope;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
