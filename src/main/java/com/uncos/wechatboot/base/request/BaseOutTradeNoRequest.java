package com.uncos.wechatboot.base.request;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlCData;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

/**
 * Created by xuwen on 2016/1/19.
 */
public class BaseOutTradeNoRequest extends BasePayRequest {

    protected String outTradeNo;

    @JacksonXmlProperty(localName = "out_trade_no")
    @JacksonXmlCData
    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }
}
