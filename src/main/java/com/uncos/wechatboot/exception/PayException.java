package com.uncos.wechatboot.exception;


import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.uncos.wechatboot.common.PayCode;

/**
 * 支付API调用异常
 * return_code=FAIL
 * <p/>
 * Created by xuwen on 2016/1/19.
 */
public class PayException extends Exception {

    private PayCode returnCode;
    private String returnMsg;

    public PayException() {
    }

    public PayException(PayCode returnCode, String returnMsg) {
        this.returnCode = returnCode;
        this.returnMsg = returnMsg;
    }

    @JacksonXmlProperty(localName = "return_code")
    public PayCode getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(PayCode returnCode) {
        this.returnCode = returnCode;
    }

    @JacksonXmlProperty(localName = "return_msg")
    public String getReturnMsg() {
        return returnMsg;
    }

    public void setReturnMsg(String returnMsg) {
        this.returnMsg = returnMsg;
    }
}
