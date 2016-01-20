package com.uncos.wechatboot.exception;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 微信API调用
 * Created by xuwen on 2016/1/14.
 */
public class WechatException extends Exception {

    private int errCode;
    private String errMsg;

    @JsonProperty("errcode")
    public int getErrCode() {
        return errCode;
    }

    public void setErrCode(int errCode) {
        this.errCode = errCode;
    }

    @JsonProperty("errmsg")
    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    @Override
    public String toString() {
        return "WechatException{" +
                "errCode=" + errCode +
                ", errMsg='" + errMsg + '\'' +
                '}';
    }
}
