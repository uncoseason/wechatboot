package com.uncos.wechatboot.api.pay.protocol.unifiedorder;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlCData;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.uncos.wechatboot.base.OpenidInterface;
import com.uncos.wechatboot.base.request.BaseOutTradeNoRequest;
import com.uncos.wechatboot.common.FeeType;
import com.uncos.wechatboot.common.TradeType;


/**
 * Created by xuwen on 2016/1/19.
 */
public class UnifiedorderRequest extends BaseOutTradeNoRequest implements OpenidInterface {

    private String deviceInfo;
    private String body;
    private String detail;
    private String attach;
    private FeeType feeType = FeeType.CNY;
    private int totalFee;
    private String spbillCreateIp;
    private String timeStart;
    private String timeExpire;
    private String goodsTag;
    private String notifyUrl;
    private TradeType tradeType;
    private String productId;
    private String limitPay;
    private String openid;

    @JacksonXmlProperty(localName = "device_info")
    @JacksonXmlCData
    public String getDeviceInfo() {
        return deviceInfo;
    }

    public void setDeviceInfo(String deviceInfo) {
        this.deviceInfo = deviceInfo;
    }

    @JacksonXmlCData
    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @JacksonXmlCData
    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    @JacksonXmlCData
    public String getAttach() {
        return attach;
    }

    public void setAttach(String attach) {
        this.attach = attach;
    }

    @JacksonXmlProperty(localName = "fee_type")
    @JacksonXmlCData
    public FeeType getFeeType() {
        return feeType;
    }

    public void setFeeType(FeeType feeType) {
        this.feeType = feeType;
    }

    @JacksonXmlProperty(localName = "total_fee")
    @JacksonXmlCData
    public int getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(int totalFee) {
        this.totalFee = totalFee;
    }

    @JacksonXmlProperty(localName = "spbill_create_ip")
    @JacksonXmlCData
    public String getSpbillCreateIp() {
        return spbillCreateIp;
    }

    public void setSpbillCreateIp(String spbillCreateIp) {
        this.spbillCreateIp = spbillCreateIp;
    }

    @JacksonXmlProperty(localName = "time_start")
    @JacksonXmlCData
    public String getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(String timeStart) {
        this.timeStart = timeStart;
    }

    @JacksonXmlProperty(localName = "time_expire")
    @JacksonXmlCData
    public String getTimeExpire() {
        return timeExpire;
    }

    public void setTimeExpire(String timeExpire) {
        this.timeExpire = timeExpire;
    }

    @JacksonXmlProperty(localName = "goods_tag")
    @JacksonXmlCData
    public String getGoodsTag() {
        return goodsTag;
    }

    public void setGoodsTag(String goodsTag) {
        this.goodsTag = goodsTag;
    }

    @JacksonXmlProperty(localName = "notify_url")
    @JacksonXmlCData
    public String getNotifyUrl() {
        return notifyUrl;
    }

    public void setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;
    }

    @JacksonXmlProperty(localName = "trade_type")
    @JacksonXmlCData
    public TradeType getTradeType() {
        return tradeType;
    }

    public void setTradeType(TradeType tradeType) {
        this.tradeType = tradeType;
    }

    @JacksonXmlProperty(localName = "product_id")
    @JacksonXmlCData
    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    @JacksonXmlProperty(localName = "limit_pay")
    @JacksonXmlCData
    public String getLimitPay() {
        return limitPay;
    }

    public void setLimitPay(String limitPay) {
        this.limitPay = limitPay;
    }

    @Override
    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }
}
