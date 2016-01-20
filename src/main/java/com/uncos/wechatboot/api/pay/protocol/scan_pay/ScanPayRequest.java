package com.uncos.wechatboot.api.pay.protocol.scan_pay;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.uncos.wechatboot.base.request.BasePayRequest;

/**
 * Created by xuwen on 2016/1/20.
 */
public class ScanPayRequest extends BasePayRequest {

    private String productId;
    private String timeStamp;

    @JsonProperty("product_id")
    @JacksonXmlProperty(localName = "product_id")
    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    @JsonProperty("time_stamp")
    @JacksonXmlProperty(localName = "time_stamp")
    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }
}
