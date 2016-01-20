package com.uncos.wechatboot.api.pay.protocol.unifiedorder;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.uncos.wechatboot.base.response.BasePayResponse;
import com.uncos.wechatboot.common.TradeType;

/**
 * Created by xuwen on 2016/1/19.
 */
public class UnifiedorderResponse extends BasePayResponse {

    private String deviceInfo;
    private TradeType trade_type;
    private String prepayId;
    private String codeUrl;

    @JacksonXmlProperty(localName = "device_info")
    public String getDeviceInfo() {
        return deviceInfo;
    }

    public void setDeviceInfo(String deviceInfo) {
        this.deviceInfo = deviceInfo;
    }

    @JacksonXmlProperty(localName = "trade_type")
    public TradeType getTrade_type() {
        return trade_type;
    }

    public void setTrade_type(TradeType trade_type) {
        this.trade_type = trade_type;
    }

    @JacksonXmlProperty(localName = "prepay_id")
    public String getPrepayId() {
        return prepayId;
    }

    public void setPrepayId(String prepayId) {
        this.prepayId = prepayId;
    }

    @JacksonXmlProperty(localName = "code_url")
    public String getCodeUrl() {
        return codeUrl;
    }

    public void setCodeUrl(String codeUrl) {
        this.codeUrl = codeUrl;
    }
}
