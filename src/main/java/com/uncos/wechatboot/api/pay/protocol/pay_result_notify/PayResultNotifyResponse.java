package com.uncos.wechatboot.api.pay.protocol.pay_result_notify;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.uncos.wechatboot.base.OpenidInterface;
import com.uncos.wechatboot.base.request.BaseTransactionIdRequest;
import com.uncos.wechatboot.common.FeeType;
import com.uncos.wechatboot.common.TradeState;
import com.uncos.wechatboot.common.TradeType;
import com.uncos.wechatboot.common.YN;

import java.util.List;

/**
 * Created by xuwen on 2016/1/19.
 */
public class PayResultNotifyResponse extends BaseTransactionIdRequest implements OpenidInterface {

    private String deviceInfo;
    private String openid;
    private YN isSubscribe;
    private TradeType tradeType;
    private TradeState tradeState;
    private String bankType;
    private int totalFee;
    private FeeType feeType;
    private int cashFee;
    private FeeType cashFeeType;
    private int couponFee;
    private int couponCount;
    private List<String> couponBatchId$n;
    private List<String> couponId$n;
    private List<Integer> couponFee$n;
    private String attach;
    private String timeEnd;

    @JacksonXmlProperty(localName = "device_info")
    public String getDeviceInfo() {
        return deviceInfo;
    }

    public void setDeviceInfo(String deviceInfo) {
        this.deviceInfo = deviceInfo;
    }

    @Override
    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    @JacksonXmlProperty(localName = "is_subscribe")
    public YN getIsSubscribe() {
        return isSubscribe;
    }

    public void setIsSubscribe(YN isSubscribe) {
        this.isSubscribe = isSubscribe;
    }

    @JacksonXmlProperty(localName = "trade_type")
    public TradeType getTradeType() {
        return tradeType;
    }

    public void setTradeType(TradeType tradeType) {
        this.tradeType = tradeType;
    }

    @JacksonXmlProperty(localName = "trade_state")
    public TradeState getTradeState() {
        return tradeState;
    }

    public void setTradeState(TradeState tradeState) {
        this.tradeState = tradeState;
    }

    @JacksonXmlProperty(localName = "bank_type")
    public String getBankType() {
        return bankType;
    }

    public void setBankType(String bankType) {
        this.bankType = bankType;
    }

    @JacksonXmlProperty(localName = "total_fee")
    public int getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(int totalFee) {
        this.totalFee = totalFee;
    }

    @JacksonXmlProperty(localName = "fee_type")
    public FeeType getFeeType() {
        return feeType;
    }

    public void setFeeType(FeeType feeType) {
        this.feeType = feeType;
    }

    @JacksonXmlProperty(localName = "cash_fee")
    public int getCashFee() {
        return cashFee;
    }

    public void setCashFee(int cashFee) {
        this.cashFee = cashFee;
    }

    @JacksonXmlProperty(localName = "cash_fee_type")
    public FeeType getCashFeeType() {
        return cashFeeType;
    }

    public void setCashFeeType(FeeType cashFeeType) {
        this.cashFeeType = cashFeeType;
    }

    @JacksonXmlProperty(localName = "coupon_fee")
    public int getCouponFee() {
        return couponFee;
    }

    public void setCouponFee(int couponFee) {
        this.couponFee = couponFee;
    }

    @JacksonXmlProperty(localName = "coupon_count")
    public int getCouponCount() {
        return couponCount;
    }

    public void setCouponCount(int couponCount) {
        this.couponCount = couponCount;
    }

    public List<String> getCouponBatchId$n() {
        return couponBatchId$n;
    }

    public void setCouponBatchId$n(List<String> couponBatchId$n) {
        this.couponBatchId$n = couponBatchId$n;
    }

    public List<String> getCouponId$n() {
        return couponId$n;
    }

    public void setCouponId$n(List<String> couponId$n) {
        this.couponId$n = couponId$n;
    }

    public List<Integer> getCouponFee$n() {
        return couponFee$n;
    }

    public void setCouponFee$n(List<Integer> couponFee$n) {
        this.couponFee$n = couponFee$n;
    }

    public String getAttach() {
        return attach;
    }

    public void setAttach(String attach) {
        this.attach = attach;
    }

    @JacksonXmlProperty(localName = "time_end")
    public String getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(String timeEnd) {
        this.timeEnd = timeEnd;
    }
}
