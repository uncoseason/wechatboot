package com.uncos.wechatboot.api.pay.protocol.refundquery;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlCData;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.uncos.wechatboot.base.request.BaseRefundRequest;

/**
 * Created by xuwen on 2016/1/19.
 */
public class RefundqueryRequest extends BaseRefundRequest {

    private String refundId;

    @JacksonXmlProperty(localName = "refund_id")
    @JacksonXmlCData
    public String getRefundId() {
        return refundId;
    }

    public void setRefundId(String refundId) {
        this.refundId = refundId;
    }
}
