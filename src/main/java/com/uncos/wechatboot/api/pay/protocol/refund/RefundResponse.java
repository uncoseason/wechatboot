package com.uncos.wechatboot.api.pay.protocol.refund;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.uncos.wechatboot.base.response.BaseTransactionIdResponse;
import com.uncos.wechatboot.common.FeeType;
import com.uncos.wechatboot.common.RefundChannel;

/**
 * Created by xuwen on 2016/1/19.
 */
public class RefundResponse extends BaseTransactionIdResponse {

    private String deviceInfo;
    private String outRefundNo;
    private String refundId;
    private RefundChannel refundChannel;
    private int refundFee;
    private int totalFee;
    private FeeType feeType;
    private int cashFee;
    private int cashRefundFee;
    private int couponRefundFee;
    private int couponRefundCount;
    private int couponRefundId;

    @JacksonXmlProperty(localName = "device_info")
    public String getDeviceInfo() {
        return deviceInfo;
    }

    public void setDeviceInfo(String deviceInfo) {
        this.deviceInfo = deviceInfo;
    }

    @JacksonXmlProperty(localName = "out_refund_no")
    public String getOutRefundNo() {
        return outRefundNo;
    }

    public void setOutRefundNo(String outRefundNo) {
        this.outRefundNo = outRefundNo;
    }

    @JacksonXmlProperty(localName = "refund_id")
    public String getRefundId() {
        return refundId;
    }

    public void setRefundId(String refundId) {
        this.refundId = refundId;
    }

    @JacksonXmlProperty(localName = "refund_channel")
    public RefundChannel getRefundChannel() {
        return refundChannel;
    }

    public void setRefundChannel(RefundChannel refundChannel) {
        this.refundChannel = refundChannel;
    }

    @JacksonXmlProperty(localName = "refund_fee")
    public int getRefundFee() {
        return refundFee;
    }

    public void setRefundFee(int refundFee) {
        this.refundFee = refundFee;
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

    @JacksonXmlProperty(localName = "cash_refund_fee")
    public int getCashRefundFee() {
        return cashRefundFee;
    }

    public void setCashRefundFee(int cashRefundFee) {
        this.cashRefundFee = cashRefundFee;
    }

    @JacksonXmlProperty(localName = "coupon_refund_fee")
    public int getCouponRefundFee() {
        return couponRefundFee;
    }

    public void setCouponRefundFee(int couponRefundFee) {
        this.couponRefundFee = couponRefundFee;
    }

    @JacksonXmlProperty(localName = "coupon_refund_count")
    public int getCouponRefundCount() {
        return couponRefundCount;
    }

    public void setCouponRefundCount(int couponRefundCount) {
        this.couponRefundCount = couponRefundCount;
    }

    @JacksonXmlProperty(localName = "coupon_refund_id")
    public int getCouponRefundId() {
        return couponRefundId;
    }

    public void setCouponRefundId(int couponRefundId) {
        this.couponRefundId = couponRefundId;
    }
}
