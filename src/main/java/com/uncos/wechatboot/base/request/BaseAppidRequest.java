package com.uncos.wechatboot.base.request;

import com.uncos.wechatboot.api.Wechatboot;
import com.uncos.wechatboot.base.AppidInterface;

/**
 * Created by xuwen on 2016/1/18.
 */
public class BaseAppidRequest implements AppidInterface {

    protected String appid = Wechatboot.config().getAppid();

    @Override
    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }
}
