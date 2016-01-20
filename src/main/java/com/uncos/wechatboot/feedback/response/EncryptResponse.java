package com.uncos.wechatboot.feedback.response;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlCData;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

/**
 * Created by xuwen on 2016/1/19.
 */
public class EncryptResponse {

    private String encrypt;
    private String msgSignature;
    private String timeStamp;
    private String nonce;

    @JacksonXmlProperty(localName = "Encrypt")
    @JacksonXmlCData
    public String getEncrypt() {
        return encrypt;
    }

    public void setEncrypt(String encrypt) {
        this.encrypt = encrypt;
    }

    @JacksonXmlProperty(localName = "MsgSignature")
    @JacksonXmlCData
    public String getMsgSignature() {
        return msgSignature;
    }

    public void setMsgSignature(String msgSignature) {
        this.msgSignature = msgSignature;
    }

    @JacksonXmlProperty(localName = "TimeStamp")
    @JacksonXmlCData
    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    @JacksonXmlProperty(localName = "Nonce")
    @JacksonXmlCData
    public String getNonce() {
        return nonce;
    }

    public void setNonce(String nonce) {
        this.nonce = nonce;
    }
}
