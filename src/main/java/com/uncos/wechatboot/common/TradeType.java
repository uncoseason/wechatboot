package com.uncos.wechatboot.common;

/**
 * 交易类型
 * Created by xuwen on 2016/1/19.
 */
public enum TradeType {
    // 统一下单
    /**
     * 公众号支付
     */
    JSAPI,
    /**
     * 原生扫码支付
     */
    NATIVE,
    /**
     * app支付
     */
    APP,
    // 刷卡支付，刷卡支付有单独的支付接口，不调用统一下单接口
    /**
     * 刷卡支付
     */
    MICROPAY,
}
