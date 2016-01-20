package com.uncos.wechatboot.api.user.protocol.user_get;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.uncos.wechatboot.base.request.BaseAccessTokenRequest;

/**
 * Created by xuwen on 2016/1/17.
 */
public class UserGetRequest extends BaseAccessTokenRequest {

    private String nextOpenid;

    @JsonProperty("next_openid")
    public String getNextOpenid() {
        return nextOpenid;
    }

    public void setNextOpenid(String nextOpenid) {
        this.nextOpenid = nextOpenid;
    }
}
