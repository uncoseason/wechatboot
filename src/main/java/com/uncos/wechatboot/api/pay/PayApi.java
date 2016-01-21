package com.uncos.wechatboot.api.pay;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.uncos.wechatboot.api.Wechatboot;
import com.uncos.wechatboot.api.pay.protocol.closeorder.CloseorderRequest;
import com.uncos.wechatboot.api.pay.protocol.closeorder.CloseorderResponse;
import com.uncos.wechatboot.api.pay.protocol.downloadbill.DownloadbillRequest;
import com.uncos.wechatboot.api.pay.protocol.downloadbill.DownloadbillResponse;
import com.uncos.wechatboot.api.pay.protocol.orderquery.OrderqueryRequest;
import com.uncos.wechatboot.api.pay.protocol.orderquery.OrderqueryResponse;
import com.uncos.wechatboot.api.pay.protocol.pay_result_notify.PayResultNotifyResponse;
import com.uncos.wechatboot.api.pay.protocol.refund.RefundRequest;
import com.uncos.wechatboot.api.pay.protocol.refund.RefundResponse;
import com.uncos.wechatboot.api.pay.protocol.refundquery.RefundqueryRequest;
import com.uncos.wechatboot.api.pay.protocol.refundquery.RefundqueryResponse;
import com.uncos.wechatboot.api.pay.protocol.report.ReportRequest;
import com.uncos.wechatboot.api.pay.protocol.report.ReportResponse;
import com.uncos.wechatboot.api.pay.protocol.scan_pay.ScanCallbackResponse;
import com.uncos.wechatboot.api.pay.protocol.scan_pay.ScanPayRequest;
import com.uncos.wechatboot.api.pay.protocol.scan_pay.ScanPayResponse;
import com.uncos.wechatboot.api.pay.protocol.unifiedorder.UnifiedorderRequest;
import com.uncos.wechatboot.api.pay.protocol.unifiedorder.UnifiedorderResponse;
import com.uncos.wechatboot.common.PayCode;
import com.uncos.wechatboot.common.PaymentParameter;
import com.uncos.wechatboot.common.RequestType;
import com.uncos.wechatboot.common.TradeType;
import com.uncos.wechatboot.exception.PayException;
import com.uncos.wechatboot.exception.PayResultException;
import com.uncos.wechatboot.exception.SignatureException;
import com.uncos.wechatboot.utils.*;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;

/**
 * 支付API
 * Created by xuwen on 2016/1/19.
 */
public class PayApi {

    /*统一下单*/
    private static final String API_UNIFIEDORDER = "https://api.mch.weixin.qq.com/pay/unifiedorder";
    /*查询订单*/
    private static final String API_ORDERQUERY = "https://api.mch.weixin.qq.com/pay/orderquery";
    /*关闭订单*/
    private static final String API_CLOSEORDER = "https://api.mch.weixin.qq.com/pay/closeorder";
    /*申请退款*/
    private static final String API_REFUND = "https://api.mch.weixin.qq.com/secapi/pay/refund";
    /*查询退款*/
    private static final String API_REFUNDQUERY = "https://api.mch.weixin.qq.com/pay/refundquery";
    /*下载对账单*/
    private static final String API_DOWNLOADBILL = "https://api.mch.weixin.qq.com/pay/downloadbill";
    /*测速上报*/
    private static final String API_REPORT = "https://api.mch.weixin.qq.com/payitil/report";
    /*生成扫码支付链接*/
    private static final String API_SCAN_PAY = "weixin://wxpay/bizpayurl?sign=XXXXX&appid=XXXXX&mch_id=XXXXX&product_id=XXXXXX&time_stamp=XXXXXX&nonce_str=XXXXX";

    private static PayApi instance = new PayApi();

    public static PayApi instance() {
        return instance;
    }

    /**
     * 统一下单
     * <p>参考<a href="https://pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=9_1">开发文档</p>
     *
     * @param request
     * @return
     * @throws PayException
     * @throws PayResultException
     */
    public UnifiedorderResponse unifiedorder(UnifiedorderRequest request) throws PayException, PayResultException {
        request.setSign(signature(request));
        String xml = Http.post(API_UNIFIEDORDER, request, RequestType.XML);
        checkSignature(xml);
        Checker.checkPay(xml);
        Checker.checkPayResult(xml);
        UnifiedorderResponse response = Converter.fromXML(xml, UnifiedorderResponse.class);
        return response;
    }

    /**
     * 查询订单
     * <p>参考<a href="https://pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=9_2">开发文档</p>
     *
     * @param request
     * @return
     * @throws PayException
     * @throws PayResultException
     */
    public OrderqueryResponse orderquery(OrderqueryRequest request) throws PayException, PayResultException {
        request.setSign(signature(request));
        String xml = Http.post(API_ORDERQUERY, request, RequestType.XML);
        checkSignature(xml);
        Checker.checkPay(xml);
        Checker.checkPayResult(xml);
        OrderqueryResponse response = Converter.fromXML(xml, OrderqueryResponse.class);
        try {
            parseCouponsForOrderquery(xml, response);
        } catch (Exception e) {
            PayException exception = new PayException(PayCode.FAIL, "解析代金券或立减优惠失败");
            throw exception;
        }
        return response;
    }

    /**
     * 关闭订单
     * <p>参考<a href="https://pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=9_2">开发文档</p>
     *
     * @param request
     * @return
     * @throws PayException
     * @throws PayResultException
     */
    public CloseorderResponse closeorder(CloseorderRequest request) throws PayException, PayResultException {
        request.setSign(signature(request));
        String xml = Http.post(API_CLOSEORDER, request, RequestType.XML);
        checkSignature(xml);
        Checker.checkPay(xml);
        Checker.checkPayResult(xml);
        CloseorderResponse response = Converter.fromXML(xml, CloseorderResponse.class);
        return response;
    }

    /**
     * 申请退款
     * <p>参考<a href="https://pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=9_4">开发文档</p>
     *
     * @param request
     * @return
     * @throws PayException
     * @throws PayResultException
     */
    public RefundResponse refund(RefundRequest request) throws PayException, PayResultException {
        request.setSign(signature(request));
        String xml = Http.post(API_REFUND, request, RequestType.XML);
        checkSignature(xml);
        Checker.checkPay(xml);
        Checker.checkPayResult(xml);
        RefundResponse response = Converter.fromXML(xml, RefundResponse.class);
        return response;
    }

    /**
     * 查询退款
     * <p>参考<a href="https://pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=9_4">开发文档</p>
     *
     * @param request
     * @return
     * @throws PayException
     * @throws PayResultException
     */
    public RefundqueryResponse refundquery(RefundqueryRequest request) throws PayException, PayResultException {
        request.setSign(signature(request));
        String xml = Http.post(API_REFUNDQUERY, request, RequestType.XML);
        checkSignature(xml);
        Checker.checkPay(xml);
        Checker.checkPayResult(xml);
        RefundqueryResponse response = Converter.fromXML(xml, RefundqueryResponse.class);
        try {
            parseCouponsForRefundquery(xml, response);
        } catch (Exception e) {
            PayException exception = new PayException(PayCode.FAIL, "解析代金券或立减优惠失败");
            throw exception;
        }
        return response;
    }

    /**
     * 下载对账单
     * <p>参考<a href="https://pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=9_6">开发文档</p>
     *
     * @param request
     * @return
     * @throws PayException
     */
    public DownloadbillResponse downloadbill(DownloadbillRequest request) throws PayException, IOException {
        request.setSign(signature(request));
        String xml = Http.post(API_DOWNLOADBILL, request, RequestType.XML);
        PayException exception = null;
        try {
            exception = Converter.fromXML(xml, PayException.class);
        } catch (Exception e) {
            // 如果不是XML则说明对账单下载成功
        }
        if (exception != null) {
            throw exception;
        } else {
            DownloadbillResponse response = new DownloadbillResponse();
            File file = File.createTempFile(String.format("微信对账单[%s]%s", request.getAppid(), request.getBillDate()), ".txt");
            FileUtils.write(file, xml);
            response.setFile(file);
            return response;
        }
    }

    /**
     * 测速上报
     * <p>参考<a href="https://pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=9_8">开发文档</p>
     *
     * @param request
     * @return
     * @throws PayException
     * @throws PayResultException
     */
    public ReportResponse report(ReportRequest request) throws PayException, PayResultException {
        request.setSign(signature(request));
        String xml = Http.post(API_REPORT, request, RequestType.XML);
        // 测速上报不会返回签名
//        checkSignature(xml);
        Checker.checkPay(xml);
        Checker.checkPayResult(xml);
        ReportResponse response = Converter.fromXML(xml, ReportResponse.class);
        return response;
    }

    /**
     * 封装支付结果通知
     * <p/>
     * <b>注意：同样的通知可能会多次发送给商户系统。商户系统必须能够正确处理重复的通知。 </b>
     * <p><a href="https://pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=9_7">开发文档</p>
     *
     * @param servletRequest
     * @return
     * @throws PayException
     * @throws PayResultException
     */
    public PayResultNotifyResponse parsePayResultNotify(ServletRequest servletRequest, ServletResponse servletResponse) throws PayException, PayResultException {
        PayException exception = new PayException(PayCode.SUCCESS, "OK");
        String xml;
        try {
            xml = IOUtils.toString(servletRequest.getInputStream(), Wechatboot.charset());
        } catch (IOException e) {
            exception = new PayException(PayCode.FAIL, "支付结果通知数据解析失败");
            responseToWechat(servletResponse, Converter.toXML(exception));
            throw exception;
        }
        Checker.checkPay(xml);
        try {
            checkSignature(xml);
        } catch (SignatureException e) {
            exception = new PayException(PayCode.FAIL, "签名校验失败");
            responseToWechat(servletResponse, Converter.toXML(exception));
            throw e;
        }
        Checker.checkPayResult(xml);
        PayResultNotifyResponse response = Converter.fromXML(xml, PayResultNotifyResponse.class);
        try {
            parseCouponsForPayResultNotify(xml, response);
        } catch (Exception e) {
            exception = new PayException(PayCode.FAIL, "解析代金券或立减优惠失败");
            responseToWechat(servletResponse, Converter.toXML(exception));
            throw exception;
        }
        responseToWechat(servletResponse, Converter.toXML(exception));
        return response;
    }

    /**
     * 解析扫码支付回调
     *
     * @param servletRequest
     * @return
     */
    public ScanCallbackResponse parseScanCallback(ServletRequest servletRequest) throws PayException {
        PayException exception;
        String xml;
        try {
            xml = IOUtils.toString(servletRequest.getInputStream(), Wechatboot.charset());
        } catch (IOException e) {
            exception = new PayException(PayCode.FAIL, "支付结果通知数据解析失败");
            throw exception;
        }
        ScanCallbackResponse response = Converter.fromXML(xml, ScanCallbackResponse.class);
        return response;
    }

    /**
     * 构建扫码支付URL
     *
     * @param request
     * @return
     */
    public String buildScanPayUrl(ScanPayRequest request) {
        request.setSign(signature(request));
        return Http.buildObjectParamURL(API_SCAN_PAY, request);
    }

    /**
     * 构建扫码支付URL
     *
     * @param productId 商户定义的商品id 或者订单号
     * @return
     */
    public String buildScanPayUrl(String productId) {
        ScanPayRequest request = new ScanPayRequest();
        request.setProductId(productId);
        request.setTimeStamp(DateUtils.getWechatTimestamp());
        request.setNonceStr(RandomStringGenerator.generate());
        return buildScanPayUrl(request);
    }

    /**
     * 构建H5调用支付的参数对象
     *
     * @param prepayId
     * @return
     */
    public PaymentParameter buildPaymentParameter(String prepayId) {
        PaymentParameter config = new PaymentParameter();
        config.setTimeStamp(DateUtils.getWechatTimestamp());
        config.setNonceStr(RandomStringGenerator.generate());
        config.setPackageWithPrepayId("prepay_id=" + prepayId);
        config.setPaySign(signature(config));
        return config;
    }

    /**
     * 统一下单（扫码支付模式一）
     *
     * @param request
     * @return
     */
    public ScanPayResponse unifiedorderForScan(UnifiedorderRequest request) {
        request.setTradeType(TradeType.NATIVE);
        ScanPayResponse scanPayResponse;
        UnifiedorderResponse unifiedorderResponse;
        try {
            request.setSign(signature(request));
            String xml = Http.post(API_UNIFIEDORDER, request, RequestType.XML);
            // 出错的情况是不会返回签名的
//            checkSignature(xml);
            Checker.checkPay(xml);
            Checker.checkPayResult(xml);
            unifiedorderResponse = Converter.fromXML(xml, UnifiedorderResponse.class);
        } catch (PayException e) {
            scanPayResponse = new ScanPayResponse();
            scanPayResponse.setReturnCode(e.getReturnCode());
            scanPayResponse.setReturnMsg(e.getReturnMsg());
            return scanPayResponse;
        } catch (PayResultException e) {
            scanPayResponse = new ScanPayResponse();
            scanPayResponse.setResultCode(e.getResultCode());
            scanPayResponse.setErrCodeDes(e.getErrCodeDes());
            return scanPayResponse;
        }
        return buildScanPayResponse(unifiedorderResponse);
    }

    /**
     * 统一下单（扫码支付模式一）
     *
     * @param request
     * @return
     */
    public String unifiedorderForScanXML(UnifiedorderRequest request) {
        return Converter.toXML(unifiedorderForScan(request));
    }

    /**
     * 构建扫码支付响应（扫码支付模式一）
     *
     * @param unifiedorderResponse 下单结果
     * @return
     */
    private ScanPayResponse buildScanPayResponse(UnifiedorderResponse unifiedorderResponse) {
        ScanPayResponse response = new ScanPayResponse();
        response.setNonceStr(unifiedorderResponse.getNonceStr());
        response.setPrepayId(unifiedorderResponse.getPrepayId());
        response.setReturnCode(PayCode.SUCCESS);
        response.setResultCode(PayCode.SUCCESS);
        response.setSign(signature(response));
        return response;
    }

    /**
     * 商户处理支付结果通知后同步返回给微信参数
     *
     * @param servletResponse
     * @param postData
     * @throws PayException
     */
    private static void responseToWechat(ServletResponse servletResponse, String postData) throws PayException {
        try {
            IOUtils.write(postData, servletResponse.getOutputStream(), Wechatboot.charset());
        } catch (IOException e) {
            throw new PayException(PayCode.FAIL, "支付结果通知同步返回失败");
        }
    }

    /**
     * 解析支付结果通知的代金券或立减优惠
     *
     * @param xml
     * @param payResultNotifyResponse
     * @throws javax.xml.parsers.ParserConfigurationException
     * @throws java.io.IOException
     * @throws org.xml.sax.SAXException
     */
    private static void parseCouponsForPayResultNotify(String xml, PayResultNotifyResponse payResultNotifyResponse) {
        List<String> coupon_id_$n = new ArrayList<String>();
        List<Integer> coupon_fee_$n = new ArrayList<Integer>();
        Map<String, Object> mapFromPayResultNotifyXML = Converter.fromXML(xml, HashMap.class);
        Iterator<String> iterator = mapFromPayResultNotifyXML.keySet().iterator();
        while (iterator.hasNext()) {
            String key = iterator.next();
            // 解析代金券或立减优惠，$n为下标，从0开始编号
            if (key.matches("^coupon_id_[0-9]+$")) { // coupon_id_$n
                coupon_id_$n.add(mapFromPayResultNotifyXML.get(key).toString());
            } else if (key.matches("^coupon_fee_[0-9]+$")) { // coupon_fee_$n
                coupon_fee_$n.add(Integer.valueOf(mapFromPayResultNotifyXML.get(key).toString()));
            }
        }
        payResultNotifyResponse.setCouponId$n(coupon_id_$n);
        payResultNotifyResponse.setCouponFee$n(coupon_fee_$n);
    }

    /**
     * 解析查询订单的代金券或立减优惠
     *
     * @param xml
     * @param orderqueryResponse
     * @throws javax.xml.parsers.ParserConfigurationException
     * @throws java.io.IOException
     * @throws org.xml.sax.SAXException
     */
    private static void parseCouponsForOrderquery(String xml, OrderqueryResponse orderqueryResponse) {
        List<String> coupon_batch_id_$n = new ArrayList<String>();
        List<String> coupon_id_$n = new ArrayList<String>();
        List<Integer> coupon_fee_$n = new ArrayList<Integer>();
        Map<String, Object> mapFromPayResultNotifyXML = Converter.fromXML(xml, HashMap.class);
        Iterator<String> iterator = mapFromPayResultNotifyXML.keySet().iterator();
        while (iterator.hasNext()) {
            String key = iterator.next();
            // 解析代金券或立减优惠，$n为下标，从0开始编号
            if (key.matches("^coupon_batch_id_[0-9]+$")) { // coupon_batch_id_$n
                coupon_batch_id_$n.add(mapFromPayResultNotifyXML.get(key).toString());
            } else if (key.matches("^coupon_id_[0-9]+$")) { // coupon_id_$n
                coupon_id_$n.add(mapFromPayResultNotifyXML.get(key).toString());
            } else if (key.matches("^coupon_fee_[0-9]+$")) { // coupon_fee_$n
                coupon_fee_$n.add(Integer.valueOf(mapFromPayResultNotifyXML.get(key).toString()));
            }
        }
        orderqueryResponse.setCouponBatchId$n(coupon_batch_id_$n);
        orderqueryResponse.setCouponId$n(coupon_id_$n);
        orderqueryResponse.setCouponFee$n(coupon_fee_$n);
    }

    /**
     * 解析查询退款的代金券或立减优惠
     *
     * @param xml
     * @param refundqueryResponse
     * @throws javax.xml.parsers.ParserConfigurationException
     * @throws java.io.IOException
     * @throws org.xml.sax.SAXException
     */
    private static void parseCouponsForRefundquery(String xml, RefundqueryResponse refundqueryResponse) {
        List<String> out_refund_no_$n = new ArrayList<String>();
        List<String> refund_id_$n = new ArrayList<String>();
        List<String> refund_channel_$n = new ArrayList<String>();
        List<Integer> refund_fee_$n = new ArrayList<Integer>();
        List<Integer> coupon_refund_fee_$n = new ArrayList<Integer>();
        List<Integer> coupon_refund_count_$n = new ArrayList<Integer>();
        List<List<String>> coupon_refund_batch_id_$n_$m = new ArrayList<List<String>>();
        List<List<String>> coupon_refund_id_$n_$m = new ArrayList<List<String>>();
        List<List<Integer>> coupon_refund_fee_$n_$m = new ArrayList<List<Integer>>();
        List<String> refund_status_$n = new ArrayList<String>();
        Map<String, Object> mapFromPayResultNotifyXML = Converter.fromXML(xml, HashMap.class);
        Iterator<String> iterator = mapFromPayResultNotifyXML.keySet().iterator();
        while (iterator.hasNext()) {
            String key = iterator.next();
            // 解析代金券或立减优惠，$n为下标，$n为下标，从0开始编号
            if (key.matches("^out_refund_no_[0-9]+$")) { // out_refund_no_$n
                out_refund_no_$n.add(mapFromPayResultNotifyXML.get(key).toString());
            } else if (key.matches("^refund_id_[0-9]+$")) { // refund_id_$n
                refund_id_$n.add(mapFromPayResultNotifyXML.get(key).toString());
            } else if (key.matches("^refund_channel_[0-9]+$")) { // refund_channel_$n
                refund_channel_$n.add(mapFromPayResultNotifyXML.get(key).toString());
            } else if (key.matches("^refund_fee_[0-9]+$")) { // refund_fee_$n
                refund_fee_$n.add(Integer.valueOf(mapFromPayResultNotifyXML.get(key).toString()));
            } else if (key.matches("^coupon_refund_fee_[0-9]+$")) { // coupon_refund_fee_$n
                coupon_refund_fee_$n.add(Integer.valueOf(mapFromPayResultNotifyXML.get(key).toString()));
            } else if (key.matches("^coupon_refund_fee_[0-9]+$")) { // coupon_refund_fee_$n
                coupon_refund_fee_$n.add(Integer.valueOf(mapFromPayResultNotifyXML.get(key).toString()));
            } else if (key.matches("^coupon_refund_count_[0-9]+$")) { // coupon_refund_count_$n
                coupon_refund_count_$n.add(Integer.valueOf(mapFromPayResultNotifyXML.get(key).toString()));
            } else if (key.matches("^coupon_refund_batch_id_[0-9]+_[0-9]+$")) { // coupon_refund_batch_id_$n_$m
                String[] indexs = key.replace("coupon_refund_batch_id_", "").split("_");
                int index0 = Integer.valueOf(indexs[0]);
                if (coupon_refund_batch_id_$n_$m.size() <= index0) {
                    coupon_refund_batch_id_$n_$m.add(new ArrayList<String>());
                }
                coupon_refund_batch_id_$n_$m.get(index0).add(mapFromPayResultNotifyXML.get(key).toString());
            } else if (key.matches("^coupon_refund_id_[0-9]+_[0-9]+$")) { // coupon_refund_id_$n_$m
                String[] indexs = key.replace("coupon_refund_id_", "").split("_");
                int index0 = Integer.valueOf(indexs[0]);
                if (coupon_refund_id_$n_$m.size() <= index0) {
                    coupon_refund_id_$n_$m.add(new ArrayList<String>());
                }
                coupon_refund_id_$n_$m.get(index0).add(mapFromPayResultNotifyXML.get(key).toString());
            } else if (key.matches("^coupon_refund_fee_[0-9]+_[0-9]+$")) { // coupon_refund_fee_$n_$m
                String[] indexs = key.replace("coupon_refund_fee_", "").split("_");
                int index0 = Integer.valueOf(indexs[0]);
                if (coupon_refund_fee_$n_$m.size() <= index0) {
                    coupon_refund_fee_$n_$m.add(new ArrayList<Integer>());
                }
                coupon_refund_fee_$n_$m.get(index0).add(Integer.valueOf(mapFromPayResultNotifyXML.get(key).toString()));
            } else if (key.matches("^refund_status_[0-9]+$")) { // refund_status_$n
                refund_status_$n.add(mapFromPayResultNotifyXML.get(key).toString());
            }
        }
        refundqueryResponse.setOutRefundNo$n(out_refund_no_$n);
        refundqueryResponse.setRefundId$n(refund_id_$n);
        refundqueryResponse.setRefundChannel$n(refund_channel_$n);
        refundqueryResponse.setRefundFee$n(refund_fee_$n);
        refundqueryResponse.setCouponRefundFee$n(coupon_refund_fee_$n);
        refundqueryResponse.setCoupon_refund_count$n(coupon_refund_count_$n);
        refundqueryResponse.setCouponRefundBatchId$n$m(coupon_refund_batch_id_$n_$m);
        refundqueryResponse.setCouponRefundId$n$m(coupon_refund_id_$n_$m);
        refundqueryResponse.setCouponRefundFee$n$m(coupon_refund_fee_$n_$m);
        refundqueryResponse.setRefundStatus$n(refund_status_$n);
    }

    /**
     * 检查支付签名
     *
     * @param xml
     */
    private void checkSignature(String xml) {
        Map<String, Object> map = Converter.fromXML(xml, HashMap.class);
        Object sign = map.get("sign");
        if (sign == null || !sign.toString().equals(signature(map))) {
            throw new SignatureException();
        }
    }

    /**
     * 支付签名
     *
     * @param object
     * @return
     */
    private String signature(Object object) {
        Map<String, Object> map = new HashMap<String, Object>();
        Class<?> clazz = object.getClass();
        do {
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                // sign不参与签名
                if ("sign".equals(field.getName())) {
                    continue;
                }
                JacksonXmlProperty jacksonXmlProperty = field.getAnnotation(JacksonXmlProperty.class);
                if (jacksonXmlProperty == null) {
                    try {
                        Method getMethod = object.getClass().getMethod("get" + field.getName().substring(0, 1).toUpperCase() + field.getName().substring(1));
                        jacksonXmlProperty = getMethod.getAnnotation(JacksonXmlProperty.class);
                    } catch (NoSuchMethodException e) {
                        // skip this
                    }
                }
                field.setAccessible(true);
                try {
                    map.put(jacksonXmlProperty != null ? jacksonXmlProperty.localName() : field.getName(), field.get(object));
                } catch (IllegalAccessException e) {
                    // never throws...
                }
            }
        } while ((clazz = clazz.getSuperclass()) != Object.class);
        return signature(map);
    }

    /**
     * 支付签名
     *
     * @param map
     * @return
     */
    private String signature(Map<String, Object> map) {
        map.put("sign", ""); // sign不参与签名
        ArrayList<String> list = new ArrayList<String>();
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            if (entry.getValue() != null && !"".equals(entry.getValue())) {
                list.add(entry.getKey() + "=" + entry.getValue() + "&");
            }
        }
        int size = list.size();
        String[] arrayToSort = list.toArray(new String[size]);
        // 字典序
        Arrays.sort(arrayToSort, String.CASE_INSENSITIVE_ORDER);
        StringBuilder signatureTemp = new StringBuilder();
        for (int i = 0; i < size; i++) {
            signatureTemp.append(arrayToSort[i]);
        }
        signatureTemp.append("key=").append(Wechatboot.config().getMchKey());
        String signatureSource = signatureTemp.toString();
        return DigestUtils.md5Hex(signatureSource).toUpperCase();
    }
}
