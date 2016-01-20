package com.uncos.wechatboot.base.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.uncos.wechatboot.base.AccessTokenInterface;
import com.uncos.wechatboot.utils.CredentialCenter;

/**
 * Created by xuwen on 2016/1/15.
 */
public class BaseAccessTokenRequest implements AccessTokenInterface {

    protected String accessToken = CredentialCenter.accessToken(); // 调用接口凭证

    @JsonProperty("access_token")
    @JacksonXmlProperty(localName = "access_token")
    @Override
    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}
