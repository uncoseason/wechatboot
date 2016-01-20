package com.uncos.wechatboot.api.pay.protocol.orderquery;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.uncos.wechatboot.api.pay.protocol.pay_result_notify.PayResultNotifyResponse;

/**
 * Created by xuwen on 2016/1/19.
 */
public class OrderqueryResponse extends PayResultNotifyResponse {

    private String tradeStateDesc;

    @JacksonXmlProperty(localName = "trade_state")
    public String getTradeStateDesc() {
        return tradeStateDesc;
    }

    public void setTradeStateDesc(String tradeStateDesc) {
        this.tradeStateDesc = tradeStateDesc;
    }
}
