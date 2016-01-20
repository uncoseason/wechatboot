package com.uncos.wechatboot.exception;


import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.uncos.wechatboot.common.PayCode;

/**
 * 支付业务异常
 * result_code=FAIL
 * <p/>
 * Created by xuwen on 2016/1/19.
 */
public class PayResultException extends Exception {

    private PayCode resultCode;
    private String errCode;
    private String errCodeDes;

    public PayResultException() {
    }

    public PayResultException(PayCode resultCode, String errCode, String errCodeDes) {
        this.resultCode = resultCode;
        this.errCode = errCode;
        this.errCodeDes = errCodeDes;
    }

    @JacksonXmlProperty(localName = "result_code")
    public PayCode getResultCode() {
        return resultCode;
    }

    public void setResultCode(PayCode resultCode) {
        this.resultCode = resultCode;
    }

    @JacksonXmlProperty(localName = "err_code")
    public String getErrCode() {
        return errCode;
    }

    public void setErrCode(String errCode) {
        this.errCode = errCode;
    }

    @JacksonXmlProperty(localName = "err_code_des")
    public String getErrCodeDes() {
        return errCodeDes;
    }

    public void setErrCodeDes(String errCodeDes) {
        this.errCodeDes = errCodeDes;
    }
}
