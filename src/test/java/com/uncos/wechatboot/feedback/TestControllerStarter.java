package com.uncos.wechatboot.feedback;

import com.uncos.wechatboot.api.Wechatboot;
import com.uncos.wechatboot.api.pay.protocol.pay_result_notify.PayResultNotifyResponse;
import com.uncos.wechatboot.api.pay.protocol.scan_pay.ScanCallbackResponse;
import com.uncos.wechatboot.api.pay.protocol.unifiedorder.UnifiedorderRequest;
import com.uncos.wechatboot.api.pay.protocol.unifiedorder.UnifiedorderResponse;
import com.uncos.wechatboot.common.JsApiParameter;
import com.uncos.wechatboot.common.PaymentParameter;
import com.uncos.wechatboot.common.TradeType;
import com.uncos.wechatboot.exception.PayException;
import com.uncos.wechatboot.exception.PayResultException;
import com.uncos.wechatboot.utils.Converter;
import com.uncos.wechatboot.utils.DateUtils;
import com.uncos.wechatboot.utils.RandomStringGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by xuwen on 2016/1/16.
 */
@SpringBootApplication
@RestController
public class TestControllerStarter {

    public static void main(String[] args) {
        SpringApplication.run(TestControllerStarter.class);
    }

    @RequestMapping("eduboss/WeiXinController/wechatboot")
    public String dispatcherServlet(HttpServletRequest httpServletRequest) {
        return new TestWechatbootServlet().feedback(httpServletRequest);
    }

    @RequestMapping(value = "payResultNotify")
    public void payResultNotify(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws PayException, PayResultException {
        PayResultNotifyResponse payResultNotifyResponse = Wechatboot.payApi().parsePayResultNotify(httpServletRequest, httpServletResponse);
        System.out.println("成功解析微信支付结果回调\n" + Converter.toXML(payResultNotifyResponse));
    }

    @RequestMapping("oauth")
    public void oauthCode(String code, String state) {
        System.out.println(String.format("接收到微信OAuth回调\ncode=%s\nstate=%s", code, state));
    }

    /**
     * 扫码支付回调
     *
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "eduboss/WeiXinController/scanPayCallback")
    public String scanPay(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        ScanCallbackResponse scanCallbackResponse = Wechatboot.payApi().parseScanCallback(httpServletRequest);
        System.out.println("收到扫码支付回调\n" + Converter.toXML(scanCallbackResponse));
        UnifiedorderRequest unifiedorderRequest = new UnifiedorderRequest();
        unifiedorderRequest.setNonceStr(RandomStringGenerator.generate());
        unifiedorderRequest.setBody("iPhone100 S PLUS");
        unifiedorderRequest.setDetail("黑科技产品");
        unifiedorderRequest.setAttach("我是商户"); // 随便写，会原样带回
        unifiedorderRequest.setOutTradeNo(RandomStringGenerator.generate()); // 我们自己的交易订单号
        unifiedorderRequest.setTotalFee(1); // 钱，单位是分
        unifiedorderRequest.setSpbillCreateIp("127.0.0.1");
        unifiedorderRequest.setTimeStart(DateUtils.format("yyyyMMddHHmmss"));
        unifiedorderRequest.setTradeType(TradeType.NATIVE);
        unifiedorderRequest.setOpenid("o40dZwL6Ik_ZsDfANjkAqd5MPpN4");
        unifiedorderRequest.setNotifyUrl("http://uncoseason.xicp.net/payResultNotify");
        // 响应扫码下单结果
        String responseXML = Wechatboot.payApi().unifiedorderForScanXML(unifiedorderRequest);
        System.out.println("响应扫码下单结果（扫码支付模式一）\n" + responseXML);
        return responseXML;
    }

    /**
     * 支付URL需要精确到二级或三级目录
     *
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "eduboss/WeiXinController/payPage")
    public String payPage() throws Exception {
        // 统一下单
        UnifiedorderRequest unifiedorderRequest = new UnifiedorderRequest();
        unifiedorderRequest.setNonceStr(RandomStringGenerator.generate());
        unifiedorderRequest.setBody("iPhone100 S PLUS");
        unifiedorderRequest.setDetail("黑科技产品");
        unifiedorderRequest.setAttach("我是商户"); // 随便写，会原样带回
        unifiedorderRequest.setOutTradeNo(RandomStringGenerator.generate()); // 我们自己的交易订单号
        unifiedorderRequest.setTotalFee(1); // 钱，单位是分
        unifiedorderRequest.setSpbillCreateIp("127.0.0.1");
        unifiedorderRequest.setTimeStart(DateUtils.format("yyyyMMddHHmmss"));
        unifiedorderRequest.setTradeType(TradeType.JSAPI);
        unifiedorderRequest.setOpenid("o40dZwL6Ik_ZsDfANjkAqd5MPpN4");
        unifiedorderRequest.setNotifyUrl("http://uncoseason.xicp.net/payResultNotify");
        UnifiedorderResponse unifiedorderResponse = Wechatboot.payApi().unifiedorder(unifiedorderRequest);
        PaymentParameter paymentParameter = Wechatboot.payApi().buildPaymentParameter(unifiedorderResponse.getPrepayId());
        StringBuffer sb = new StringBuffer();
        sb.append("Pay Page                                                                          ");
        sb.append("        <script>                                                                  ");
        sb.append("if (typeof WeixinJSBridge == 'undefined') {                                       ");
        sb.append("    if (document.addEventListener) {                                              ");
        sb.append("        document.addEventListener('WeixinJSBridgeReady', onBridgeReady, false);   ");
        sb.append("    } else if (document.attachEvent) {                                            ");
        sb.append("        document.attachEvent('WeixinJSBridgeReady', onBridgeReady);               ");
        sb.append("        document.attachEvent('onWeixinJSBridgeReady', onBridgeReady);             ");
        sb.append("    }                                                                             ");
        sb.append("} else {                                                                          ");
        sb.append("    onBridgeReady();                                                              ");
        sb.append("}                                                                                 ");
        sb.append("                                                                                  ");
        sb.append("function onBridgeReady() {                                                        ");
        sb.append("    WeixinJSBridge.invoke(                                                        ");
        sb.append("            'getBrandWCPayRequest',                                              ");
//        sb.append("    {                'appId': data.appid,                                          ");
//        sb.append("            'timeStamp': data.timeStamp,                                          ");
//        sb.append("            'nonceStr': data.nonceStr,                                            ");
//        sb.append("            'package': data.packageWithPrepayId,                                  ");
//        sb.append("            'signType': data.signType,                                            ");
//        sb.append("            'paySign':  data.paySign     }                                         ");
        sb.append(Converter.toJSON(paymentParameter));
        sb.append("    ,                                                                            ");
        sb.append("    function (res) {                                                              ");
        sb.append("        if (res.err_msg == 'get_brand_wcpay_request:ok') {                        ");
        sb.append("            alert('支付成功：' + JSON.stringify(res))                                  ");
        sb.append("        } else {                                                                  ");
        sb.append("            alert('支付失败：' + JSON.stringify(res));                                 ");
        sb.append("        }                                                                         ");
        sb.append("    }                                                                             ");
        sb.append("    );                                                                            ");
        sb.append("}                                                                                 ");
        sb.append("</script>                                                                         ");
        return sb.toString();
    }

    @RequestMapping(value = "eduboss/WeiXinController/jssdkPage")
    public String jssdkPage() throws Exception {
        JsApiParameter jsApiParameter = Wechatboot.jsApi().signatureUrl("http://uncoseason.xicp.net/eduboss/WeiXinController/jssdkPage");
        StringBuilder sb = new StringBuilder();
        sb.append("<html>                                                                                                                                                                                                       ");
        sb.append("<head>                                                                                                                                                                                                       ");
        sb.append("<title>JS SDK Page</title>                                                                                                                                                                                   ");
        sb.append("</head>                                                                                                                                                                                                      ");
        sb.append("<body>                                                                                                                                                                                                       ");
        sb.append("       JS SDK Page                                                                                                                                                                                          ");
        sb.append("</body>                                                                                                                                                                                                      ");
        sb.append("<script src=\"http://res.wx.qq.com/open/js/jweixin-1.0.0.js\"></script>                                                                                                                                        ");
        sb.append("<script>                                                                                                                                                                                                     ");
        sb.append("        wx.config({                                                                                                                                                                                          ");
        sb.append("                debug: true,                                                                                                                                                                                 ");
        sb.append("        appId: '" + jsApiParameter.getAppId() + "',                                                                                                                                                                                      ");
        sb.append("        timestamp: '" + jsApiParameter.getTimestamp() + "',                                                                                                                                                                              ");
        sb.append("        nonceStr: '" + jsApiParameter.getNonceStr() + "',                                                                                                                                                                                ");
        sb.append("        signature: '" + jsApiParameter.getSignature() + "',                                                                                                                                                                              ");
        sb.append("        jsApiList: [                                                                                                                                                                                         ");
        sb.append("'checkJsApi',                                                                                                                                                                                                ");
        sb.append("        'onMenuShareAppMessage'                                                                                                                                                                              ");
        sb.append("]                                                                                                                                                                                                            ");
        sb.append("});                                                                                                                                                                                                          ");
        sb.append("wx.ready(function () {                                                                                                                                                                                       ");
        sb.append("    wx.onMenuShareAppMessage({                                                                                                                                                                               ");
        sb.append("            title: '互联网之子',                                                                                                                                                                                  ");
        sb.append("            desc: '在长大的过程中，我才慢慢发现，我身边的所有事，别人跟我说的所有事，那些所谓本来如此，注定如此的事，它们其实没有非得如此，事情是可以改变的。更重要的是，有些事既然错了，那就该做出改变。',                                                                                             ");
        sb.append("            link: 'http://movie.douban.com/subject/25785114/',                                                                                                                                               ");
        sb.append("            imgUrl: 'http://demo.open.weixin.qq.com/jssdk/images/p2166127561.jpg',                                                                                                                           ");
        sb.append("            trigger: function (res) {  alert('用户点击发送给朋友');                                                                                                                                                                           ");
        sb.append("        /*不要尝试在trigger中使用ajax异步请求修改本次分享的内容，因为客户端分享操作是一个同步操作，这时候使用ajax的回包会还没有返回*/                                                                                                                              ");
        sb.append("    },                                                                                                                                                                                                       ");
        sb.append("    success: function (res) {                                                                                                                                                                                ");
        sb.append("        alert('已分享');                                                                                                                                                                                        ");
        sb.append("    },                                                                                                                                                                                                       ");
        sb.append("    cancel: function (res) {                                                                                                                                                                                 ");
        sb.append("        alert('已取消');                                                                                                                                                                                        ");
        sb.append("    },                                                                                                                                                                                                       ");
        sb.append("    fail: function (res) {                                                                                                                                                                                   ");
        sb.append("        alert(JSON.stringify(res));                                                                                                                                                                          ");
        sb.append("    }                                                                                                                                                                                                        ");
        sb.append("    });                                                                                                                                                                                                      ");
        sb.append("    alert('已注册获取“发送给朋友”状态事件');                                                                                                                                                                               ");
        sb.append("})                                                                                                                                                                                                           ");
        sb.append("</script>                                                                                                                                                                                                    ");
        sb.append("</html>                                                                                                                                                                                                      ");
        return sb.toString();
    }

}
