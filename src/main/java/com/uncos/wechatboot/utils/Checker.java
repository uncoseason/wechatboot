package com.uncos.wechatboot.utils;

import com.uncos.wechatboot.common.PayCode;
import com.uncos.wechatboot.exception.PayException;
import com.uncos.wechatboot.exception.PayResultException;
import com.uncos.wechatboot.exception.WechatException;

/**
 * 检查工具
 * Created by xuwen on 2016/1/15.
 */
public class Checker {

    /**
     * 检查微信公众平台API的调用结果
     *
     * @param json
     * @throws WechatException
     */
    public static void checkApiResponse(String json) throws WechatException {
        WechatException exception = Converter.fromJSON(json, WechatException.class);
        if (exception.getErrCode() != 0) {
            throw exception;
        }
    }

    /**
     * 检查微信支付的返回状态码
     *
     * @param xml
     * @throws PayException
     */
    public static void checkPay(String xml) throws PayException {
        PayException exception = Converter.fromXML(xml, PayException.class);
        if (exception.getReturnCode() == PayCode.FAIL) {
            throw exception;
        }
    }

    /**
     * 检查微信支付的业务结果
     *
     * @param xml
     * @throws PayResultException
     */
    public static void checkPayResult(String xml) throws PayResultException {
        PayResultException exception = Converter.fromXML(xml, PayResultException.class);
        if (exception.getResultCode() == PayCode.FAIL) {
            throw exception;
        }
    }

}
