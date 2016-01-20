package com.uncos.wechatboot.api.pay.protocol.downloadbill;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlCData;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.uncos.wechatboot.base.request.BasePayRequest;

/**
 * Created by xuwen on 2016/1/19.
 */
public class DownloadbillRequest extends BasePayRequest {

    private String deviceInfo;
    private String billDate;
    private String billType;

    @JacksonXmlProperty(localName = "device_info")
    @JacksonXmlCData
    public String getDeviceInfo() {
        return deviceInfo;
    }

    public void setDeviceInfo(String deviceInfo) {
        this.deviceInfo = deviceInfo;
    }

    @JacksonXmlProperty(localName = "bill_date")
    @JacksonXmlCData
    public String getBillDate() {
        return billDate;
    }

    public void setBillDate(String billDate) {
        this.billDate = billDate;
    }

    @JacksonXmlProperty(localName = "bill_type")
    @JacksonXmlCData
    public String getBillType() {
        return billType;
    }

    public void setBillType(String billType) {
        this.billType = billType;
    }
}
