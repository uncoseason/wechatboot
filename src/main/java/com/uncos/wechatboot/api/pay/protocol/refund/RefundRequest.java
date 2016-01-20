package com.uncos.wechatboot.api.pay.protocol.refund;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlCData;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.uncos.wechatboot.base.request.BaseTransactionIdRequest;
import com.uncos.wechatboot.common.FeeType;

/**
 * Created by xuwen on 2016/1/19.
 */
public class RefundRequest extends BaseTransactionIdRequest {

    private int totalFee;
    private int refundFee;
    private FeeType refundFeeType = FeeType.CNY;
    private String opUserId;

    @JacksonXmlProperty(localName = "total_fee")
    @JacksonXmlCData
    public int getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(int totalFee) {
        this.totalFee = totalFee;
    }

    @JacksonXmlProperty(localName = "refund_fee")
    @JacksonXmlCData
    public int getRefundFee() {
        return refundFee;
    }

    public void setRefundFee(int refundFee) {
        this.refundFee = refundFee;
    }

    @JacksonXmlProperty(localName = "refund_fee_type")
    @JacksonXmlCData
    public FeeType getRefundFeeType() {
        return refundFeeType;
    }

    public void setRefundFeeType(FeeType refundFeeType) {
        this.refundFeeType = refundFeeType;
    }

    @JacksonXmlProperty(localName = "op_user_id")
    @JacksonXmlCData
    public String getOpUserId() {
        return opUserId;
    }

    public void setOpUserId(String opUserId) {
        this.opUserId = opUserId;
    }
}
