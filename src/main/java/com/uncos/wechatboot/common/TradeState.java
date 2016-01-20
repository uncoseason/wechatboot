package com.uncos.wechatboot.common;

/**
 * 交易状态
 * Created by xuwen on 2016/1/19.
 */
public enum TradeState {
    /**
     * 支付成功
     */
    SUCCESS,
    /**
     * 转入退款
     */
    REFUND,
    /**
     * 未支付
     */
    NOTPAY,
    /**
     * 已关闭
     */
    CLOSED,
    /**
     * 已撤销（刷卡支付）
     */
    REVOKED,
    /**
     * 用户支付中
     */
    USERPAYING,
    /**
     * 支付失败(其他原因，如银行返回失败)
     */
    PAYERROR,
}
