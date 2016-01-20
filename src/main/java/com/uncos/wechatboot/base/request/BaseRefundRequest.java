package com.uncos.wechatboot.base.request;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlCData;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

/**
 * Created by xuwen on 2016/1/19.
 */
public class BaseRefundRequest extends BaseTransactionIdRequest {

    protected String deviceInfo;
    protected String outRefundNo;

    @JacksonXmlProperty(localName = "device_info")
    @JacksonXmlCData
    public String getDeviceInfo() {
        return deviceInfo;
    }

    public void setDeviceInfo(String deviceInfo) {
        this.deviceInfo = deviceInfo;
    }

    @JacksonXmlProperty(localName = "out_refund_no")
    @JacksonXmlCData
    public String getOutRefundNo() {
        return outRefundNo;
    }

    public void setOutRefundNo(String outRefundNo) {
        this.outRefundNo = outRefundNo;
    }
}
