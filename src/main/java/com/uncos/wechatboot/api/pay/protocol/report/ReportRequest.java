package com.uncos.wechatboot.api.pay.protocol.report;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlCData;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.uncos.wechatboot.base.request.BaseOutTradeNoRequest;
import com.uncos.wechatboot.common.PayCode;

/**
 * Created by xuwen on 2016/1/19.
 */
public class ReportRequest extends BaseOutTradeNoRequest {

    private String deviceInfo;
    private String interfaceUrl;
    private int executeTime;
    private PayCode returnCode;
    private String returnMsg;
    private PayCode resultCode;
    private String userIp;
    private String time;

    @JacksonXmlProperty(localName = "device_info")
    @JacksonXmlCData
    public String getDeviceInfo() {
        return deviceInfo;
    }

    public void setDeviceInfo(String deviceInfo) {
        this.deviceInfo = deviceInfo;
    }

    @JacksonXmlProperty(localName = "interface_url")
    @JacksonXmlCData
    public String getInterfaceUrl() {
        return interfaceUrl;
    }

    public void setInterfaceUrl(String interfaceUrl) {
        this.interfaceUrl = interfaceUrl;
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

    @JacksonXmlProperty(localName = "execute_time_")
    @JacksonXmlCData
    public int getExecuteTime() {
        return executeTime;
    }

    public void setExecuteTime(int executeTime) {
        this.executeTime = executeTime;
    }

    @JacksonXmlProperty(localName = "user_ip")
    @JacksonXmlCData
    public String getUserIp() {
        return userIp;
    }

    public void setUserIp(String userIp) {
        this.userIp = userIp;
    }

    @JacksonXmlCData
    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
