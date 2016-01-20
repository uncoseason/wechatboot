package com.uncos.wechatboot.api.pay.protocol.scan_pay;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlCData;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.uncos.wechatboot.base.response.BasePayResponse;
import com.uncos.wechatboot.common.PayCode;

/**
 * Created by xuwen on 2016/1/20.
 */
public class ScanPayResponse extends BasePayResponse {

    private String prepayId;
    private PayCode returnCode;
    private String returnMsg;
    private PayCode resultCode;
    private String errCodeDes;

    @JacksonXmlProperty(localName = "prepay_id")
    @JacksonXmlCData
    public String getPrepayId() {
        return prepayId;
    }

    public void setPrepayId(String prepayId) {
        this.prepayId = prepayId;
    }

    @JacksonXmlProperty(localName = "return_code")
    @JacksonXmlCData
    public PayCode getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(PayCode returnCode) {
        this.returnCode = returnCode;
    }

    @JacksonXmlProperty(localName = "return_msg")
    @JacksonXmlCData
    public String getReturnMsg() {
        return returnMsg;
    }

    public void setReturnMsg(String returnMsg) {
        this.returnMsg = returnMsg;
    }

    @JacksonXmlProperty(localName = "result_code")
    @JacksonXmlCData
    public PayCode getResultCode() {
        return resultCode;
    }

    public void setResultCode(PayCode resultCode) {
        this.resultCode = resultCode;
    }

    @JacksonXmlProperty(localName = "err_code_des")
    @JacksonXmlCData
    public String getErrCodeDes() {
        return errCodeDes;
    }

    public void setErrCodeDes(String errCodeDes) {
        this.errCodeDes = errCodeDes;
    }
}
