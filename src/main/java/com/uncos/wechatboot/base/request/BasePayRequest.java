package com.uncos.wechatboot.base.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlCData;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.uncos.wechatboot.api.Wechatboot;

/**
 * Created by xuwen on 2016/1/19.
 */
public class BasePayRequest extends BaseAppidRequest {

    protected String mchId = Wechatboot.config().getMchId();
    protected String nonceStr;
    protected String sign;

    @JsonProperty("mch_id")
    @JacksonXmlProperty(localName = "mch_id")
    @JacksonXmlCData
    public String getMchId() {
        return mchId;
    }

    public void setMchId(String mchId) {
        this.mchId = mchId;
    }

    @JsonProperty("nonce_str")
    @JacksonXmlProperty(localName = "nonce_str")
    @JacksonXmlCData
    public String getNonceStr() {
        return nonceStr;
    }

    public void setNonceStr(String nonceStr) {
        this.nonceStr = nonceStr;
    }

    @JacksonXmlCData
    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }
}
