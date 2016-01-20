package com.uncos.wechatboot.base.request;

import com.uncos.wechatboot.base.OpenidInterface;

/**
 * Created by xuwen on 2016-1-15.
 */
public class BaseOpenidRequest extends BaseAccessTokenRequest implements OpenidInterface {

    protected String openid; // 用户标识

    @Override
    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }
}
