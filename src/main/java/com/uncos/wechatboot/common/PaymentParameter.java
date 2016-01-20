package com.uncos.wechatboot.common;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.uncos.wechatboot.api.Wechatboot;

/**
 * H5调起支付API的参数对象
 * Created by xuwen on 2016/1/19.
 */
public class PaymentParameter {
    private String appid = Wechatboot.config().getAppid();
    private String timeStamp;
    private String nonceStr;
    private String packageWithPrepayId; // 参数名package
    private String signType = "MD5";
    private String paySign;

    @JsonProperty("appId")
    @JacksonXmlProperty(localName = "appId")
    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getNonceStr() {
        return nonceStr;
    }

    public void setNonceStr(String nonceStr) {
        this.nonceStr = nonceStr;
    }

    @JsonProperty("package")
    @JacksonXmlProperty(localName = "package")
    public String getPackageWithPrepayId() {
        return packageWithPrepayId;
    }

    public void setPackageWithPrepayId(String packageWithPrepayId) {
        this.packageWithPrepayId = packageWithPrepayId;
    }

    public String getSignType() {
        return signType;
    }

    public void setSignType(String signType) {
        this.signType = signType;
    }

    public String getPaySign() {
        return paySign;
    }

    public void setPaySign(String paySign) {
        this.paySign = paySign;
    }
}
