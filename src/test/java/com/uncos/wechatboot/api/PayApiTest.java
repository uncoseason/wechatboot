package com.uncos.wechatboot.api;

import com.uncos.wechatboot.api.pay.protocol.closeorder.CloseorderRequest;
import com.uncos.wechatboot.api.pay.protocol.closeorder.CloseorderResponse;
import com.uncos.wechatboot.api.pay.protocol.downloadbill.DownloadbillRequest;
import com.uncos.wechatboot.api.pay.protocol.downloadbill.DownloadbillResponse;
import com.uncos.wechatboot.api.pay.protocol.orderquery.OrderqueryRequest;
import com.uncos.wechatboot.api.pay.protocol.orderquery.OrderqueryResponse;
import com.uncos.wechatboot.api.pay.protocol.refund.RefundRequest;
import com.uncos.wechatboot.api.pay.protocol.refund.RefundResponse;
import com.uncos.wechatboot.api.pay.protocol.refundquery.RefundqueryRequest;
import com.uncos.wechatboot.api.pay.protocol.refundquery.RefundqueryResponse;
import com.uncos.wechatboot.api.pay.protocol.report.ReportRequest;
import com.uncos.wechatboot.api.pay.protocol.report.ReportResponse;
import com.uncos.wechatboot.api.pay.protocol.unifiedorder.UnifiedorderRequest;
import com.uncos.wechatboot.api.pay.protocol.unifiedorder.UnifiedorderResponse;
import com.uncos.wechatboot.common.PayCode;
import com.uncos.wechatboot.common.PaymentParameter;
import com.uncos.wechatboot.common.TradeType;
import com.uncos.wechatboot.exception.PayException;
import com.uncos.wechatboot.exception.PayResultException;
import com.uncos.wechatboot.utils.Converter;
import com.uncos.wechatboot.utils.RandomStringGenerator;
import org.junit.Test;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 支付API测试
 * 所有测试用例都需要配置以下项
 * wechatboot.mchId    商户ID
 * wechatboot.mchKey   商户API KEY
 * Created by xuwen on 2016/1/19.
 */
public class PayApiTest {

    @Test
    public void testOrder() throws PayException, PayResultException {
        String outTradeNo = RandomStringGenerator.generate();
        // 统一下单
        UnifiedorderRequest unifiedorderRequest = new UnifiedorderRequest();
        unifiedorderRequest.setNonceStr(RandomStringGenerator.generate());
        unifiedorderRequest.setBody("iPhone100 S PLUS");
        unifiedorderRequest.setDetail("黑科技产品");
        unifiedorderRequest.setAttach("我是商户"); // 随便写，会原样带回
        unifiedorderRequest.setOutTradeNo(outTradeNo); // 我们自己的交易订单号
        unifiedorderRequest.setTotalFee(1); // 钱，单位是分
        unifiedorderRequest.setSpbillCreateIp("127.0.0.1");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        unifiedorderRequest.setTimeStart(sdf.format(new Date()));
        unifiedorderRequest.setTradeType(TradeType.JSAPI);
        unifiedorderRequest.setOpenid("o40dZwL6Ik_ZsDfANjkAqd5MPpN4");
        unifiedorderRequest.setNotifyUrl("http://uncoseason.xicp.net/eduboss/WeiXinController/payCallback.do");
        UnifiedorderResponse unifiedorderResponse = Wechatboot.payApi().unifiedorder(unifiedorderRequest);
        System.out.println("测试 统一下单\n" + Converter.toXML(unifiedorderResponse));
        // 查询订单
        OrderqueryRequest orderqueryRequest = new OrderqueryRequest();
        orderqueryRequest.setAppid(unifiedorderResponse.getAppid());
        orderqueryRequest.setOutTradeNo(outTradeNo);
        orderqueryRequest.setNonceStr(RandomStringGenerator.generate());
        OrderqueryResponse orderqueryResponse = Wechatboot.payApi().orderquery(orderqueryRequest);
        System.out.println("测试 查询订单\n" + Converter.toXML(orderqueryResponse));
        // 关闭订单
        CloseorderRequest closeorderRequest = new CloseorderRequest();
        closeorderRequest.setAppid(unifiedorderResponse.getAppid());
        closeorderRequest.setOutTradeNo(outTradeNo);
        closeorderRequest.setNonceStr(RandomStringGenerator.generate());
        CloseorderResponse closeorderResponse = Wechatboot.payApi().closeorder(closeorderRequest);
        System.out.println("测试 关闭订单\n" + Converter.toXML(closeorderResponse));
    }

    @Test
    public void testReport() throws PayException, PayResultException {
        ReportRequest reportRequest = new ReportRequest();
        reportRequest.setNonceStr(RandomStringGenerator.generate());
        reportRequest.setInterfaceUrl("https://api.mch.weixin.qq.com/pay/unifiedorder");
        reportRequest.setExecuteTime(1000);
        reportRequest.setReturnCode(PayCode.SUCCESS);
        reportRequest.setReturnMsg("成功");
        reportRequest.setResultCode(PayCode.SUCCESS);
        reportRequest.setOutTradeNo(RandomStringGenerator.generate());
        reportRequest.setUserIp("127.0.0.1");
        reportRequest.setTime("201512130235");
        ReportResponse reportResponse = Wechatboot.payApi().report(reportRequest);
        System.out.println("测试 测速上报\n" + Converter.toXML(reportResponse));
    }

    @Test
    public void testDownloadbill() throws PayException, IOException {
        DownloadbillRequest downloadbillRequest = new DownloadbillRequest();
        downloadbillRequest.setNonceStr(RandomStringGenerator.generate());
        downloadbillRequest.setBillDate("20151211");
        downloadbillRequest.setBillType("ALL");
        DownloadbillResponse downloadbillResponse = Wechatboot.payApi().downloadbill(downloadbillRequest);
        System.out.println("测试 下载对账单\n" + downloadbillResponse.getFile().getAbsolutePath());
    }

    @Test
    public void testRefund() throws PayException, PayResultException {
        String transactionId = "1000080905201512112018328757";
        // 此用例需要安装商户证书（否则会返回HTTP443），并获取一个真实的交易ID
        String outRefundNo = RandomStringGenerator.generate();
        // 申请退款
        RefundRequest refundRequest = new RefundRequest();
        refundRequest.setNonceStr(RandomStringGenerator.generate());
        refundRequest.setTransactionId(transactionId);
        refundRequest.setOutTradeNo(outRefundNo);
        refundRequest.setTotalFee(1);
        refundRequest.setRefundFee(1);
        RefundResponse refundResponse = Wechatboot.payApi().refund(refundRequest);
        System.out.println("测试 申请退款\n" + Converter.toXML(refundResponse));
        // 查询退款
        RefundqueryRequest refundqueryRequest = new RefundqueryRequest();
        refundqueryRequest.setNonceStr(RandomStringGenerator.generate());
        refundqueryRequest.setTransactionId(transactionId);
        RefundqueryResponse refundqueryResponse = Wechatboot.payApi().refundquery(refundqueryRequest);
        System.out.println("测试 查询退款\n" + Converter.toXML(refundqueryResponse));
    }

    @Test
    public void testBuildPaymentParams() {
        PaymentParameter paymentParameter = Wechatboot.payApi().buildPaymentParameter("12345679");
        System.out.println("测试 构建H5支付参数\n" + Converter.toJSON(paymentParameter));
    }

    @Test
    public void testBuildScanPayUrl() {
        String scanPayUrl = Wechatboot.payApi().buildScanPayUrl(RandomStringGenerator.generate());
        System.out.println("测试 构建扫码支付URL（扫码支付模式一）\t" + scanPayUrl);
    }

    @Test
    public void testCodeUrlPayUrl() throws Exception{
        UnifiedorderRequest unifiedorderRequest = new UnifiedorderRequest();
        unifiedorderRequest.setNonceStr(RandomStringGenerator.generate());
        unifiedorderRequest.setBody("iPhone100 S PLUS");
        unifiedorderRequest.setDetail("黑科技产品");
        unifiedorderRequest.setAttach("我是商户"); // 随便写，会原样带回
        unifiedorderRequest.setOutTradeNo(RandomStringGenerator.generate()); // 我们自己的交易订单号
        unifiedorderRequest.setTotalFee(1); // 钱，单位是分
        unifiedorderRequest.setSpbillCreateIp("127.0.0.1");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        unifiedorderRequest.setTimeStart(sdf.format(new Date()));
        unifiedorderRequest.setTradeType(TradeType.NATIVE);
        unifiedorderRequest.setOpenid("o40dZwL6Ik_ZsDfANjkAqd5MPpN4");
        unifiedorderRequest.setNotifyUrl("http://uncoseason.xicp.net/eduboss/WeiXinController/payCallback.do");
        UnifiedorderResponse unifiedorderResponse = Wechatboot.payApi().unifiedorder(unifiedorderRequest);
        System.out.println("测试 统一下单（扫码支付模式二）\n" + Converter.toXML(unifiedorderResponse));
    }
}
