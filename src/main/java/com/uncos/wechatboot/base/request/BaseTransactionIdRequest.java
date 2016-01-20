package com.uncos.wechatboot.base.request;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlCData;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

/**
 * Created by xuwen on 2016/1/19.
 */
public class BaseTransactionIdRequest extends BaseOutTradeNoRequest {

    protected String transactionId;

    @JacksonXmlProperty(localName = "transaction_id")
    @JacksonXmlCData
    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }
}
