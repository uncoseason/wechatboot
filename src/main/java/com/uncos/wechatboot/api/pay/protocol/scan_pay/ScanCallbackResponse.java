package com.uncos.wechatboot.api.pay.protocol.scan_pay;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.uncos.wechatboot.base.request.BasePayRequest;
import com.uncos.wechatboot.common.YN;

/**
 * Created by xuwen on 2016/1/20.
 */
public class ScanCallbackResponse extends BasePayRequest{

    private YN isSubscribe;
    private String productId;

    @JacksonXmlProperty(localName = "is_subscribe")
    public YN getIsSubscribe() {
        return isSubscribe;
    }

    public void setIsSubscribe(YN isSubscribe) {
        this.isSubscribe = isSubscribe;
    }

    @JacksonXmlProperty(localName = "product_id")
    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }
}
