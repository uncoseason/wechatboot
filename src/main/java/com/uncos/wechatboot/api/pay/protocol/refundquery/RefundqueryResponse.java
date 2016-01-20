package com.uncos.wechatboot.api.pay.protocol.refundquery;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.uncos.wechatboot.base.response.BaseTransactionIdResponse;
import com.uncos.wechatboot.common.FeeType;

import java.util.List;

/**
 * Created by xuwen on 2016/1/19.
 */
public class RefundqueryResponse extends BaseTransactionIdResponse {

    private String deviceInfo;
    private int totalFee;
    private FeeType feeType;
    private int cashFee;
    private int refundCount;
    private List<String> outRefundNo$n;
    private List<String> refundId$n;
    private List<String> refundChannel$n;
    private List<Integer> refundFee$n;
    private List<Integer> couponRefundFee$n;
    private List<Integer> coupon_refund_count$n;
    private List<List<String>> couponRefundBatchId$n$m;
    private List<List<String>> couponRefundId$n$m;
    private List<List<Integer>> couponRefundFee$n$m;
    private List<String> refundStatus$n;

    @JacksonXmlProperty(localName = "device_info")
    public String getDeviceInfo() {
        return deviceInfo;
    }

    public void setDeviceInfo(String deviceInfo) {
        this.deviceInfo = deviceInfo;
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

    @JacksonXmlProperty(localName = "refund_count")
    public int getRefundCount() {
        return refundCount;
    }

    public void setRefundCount(int refundCount) {
        this.refundCount = refundCount;
    }

    public List<String> getOutRefundNo$n() {
        return outRefundNo$n;
    }

    public void setOutRefundNo$n(List<String> outRefundNo$n) {
        this.outRefundNo$n = outRefundNo$n;
    }

    public List<String> getRefundId$n() {
        return refundId$n;
    }

    public void setRefundId$n(List<String> refundId$n) {
        this.refundId$n = refundId$n;
    }

    public List<String> getRefundChannel$n() {
        return refundChannel$n;
    }

    public void setRefundChannel$n(List<String> refundChannel$n) {
        this.refundChannel$n = refundChannel$n;
    }

    public List<Integer> getRefundFee$n() {
        return refundFee$n;
    }

    public void setRefundFee$n(List<Integer> refundFee$n) {
        this.refundFee$n = refundFee$n;
    }

    public List<Integer> getCouponRefundFee$n() {
        return couponRefundFee$n;
    }

    public void setCouponRefundFee$n(List<Integer> couponRefundFee$n) {
        this.couponRefundFee$n = couponRefundFee$n;
    }

    public List<Integer> getCoupon_refund_count$n() {
        return coupon_refund_count$n;
    }

    public void setCoupon_refund_count$n(List<Integer> coupon_refund_count$n) {
        this.coupon_refund_count$n = coupon_refund_count$n;
    }

    public List<List<String>> getCouponRefundBatchId$n$m() {
        return couponRefundBatchId$n$m;
    }

    public void setCouponRefundBatchId$n$m(List<List<String>> couponRefundBatchId$n$m) {
        this.couponRefundBatchId$n$m = couponRefundBatchId$n$m;
    }

    public List<List<String>> getCouponRefundId$n$m() {
        return couponRefundId$n$m;
    }

    public void setCouponRefundId$n$m(List<List<String>> couponRefundId$n$m) {
        this.couponRefundId$n$m = couponRefundId$n$m;
    }

    public List<List<Integer>> getCouponRefundFee$n$m() {
        return couponRefundFee$n$m;
    }

    public void setCouponRefundFee$n$m(List<List<Integer>> couponRefundFee$n$m) {
        this.couponRefundFee$n$m = couponRefundFee$n$m;
    }

    public List<String> getRefundStatus$n() {
        return refundStatus$n;
    }

    public void setRefundStatus$n(List<String> refundStatus$n) {
        this.refundStatus$n = refundStatus$n;
    }
}
