package com.uncos.wechatboot.base.request;

import com.uncos.wechatboot.api.Wechatboot;
import com.uncos.wechatboot.base.AccountInterface;

/**
 * Created by xuwen on 2016/1/14.
 */
public class BaseAccountRequest extends BaseAppidRequest implements AccountInterface {

    protected String secret = Wechatboot.config().getSecret();

    @Override
    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }
}
