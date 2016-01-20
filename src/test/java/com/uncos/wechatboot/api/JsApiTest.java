package com.uncos.wechatboot.api;

import com.uncos.wechatboot.common.JsApiParameter;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.junit.Test;

/**
 * Created by xuwen on 2016/1/20.
 */
public class JsApiTest {

    @Test
    public void testSignatureUrl(){
        JsApiParameter jsApiParameter = Wechatboot.jsApi().signatureUrl("http://mp.weixin.qq.com/");
        System.out.println("测试 JSSDK URL签名" + ToStringBuilder.reflectionToString(jsApiParameter));
    }
}
