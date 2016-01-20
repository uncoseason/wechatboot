package com.uncos.wechatboot.common;

import com.uncos.wechatboot.api.Wechatboot;

/**
 * JSSDK通过config接口注入权限验证配置
 * Created by xuwen on 2016/1/20.
 */
public class JsApiParameter {

    private String appId = Wechatboot.config().getAppid();
    private String timestamp;
    private String nonceStr;
    private String signature;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getNonceStr() {
        return nonceStr;
    }

    public void setNonceStr(String nonceStr) {
        this.nonceStr = nonceStr;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }
}
