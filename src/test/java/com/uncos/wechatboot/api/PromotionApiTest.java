package com.uncos.wechatboot.api;

import com.uncos.wechatboot.api.promotion.protocol.qrcode_create.QrcodeCreateResponse;
import com.uncos.wechatboot.api.promotion.protocol.shorturl.ShorturlResponse;
import com.uncos.wechatboot.api.promotion.protocol.showqrcode.ShowqrcodeResponse;
import com.uncos.wechatboot.exception.WechatException;
import org.junit.Test;

/**
 * Created by xuwen on 2016/1/17.
 */
public class PromotionApiTest {

    @Test
    public void testCreateQrcodeAndShow() throws Exception {
        Wechatboot.promotionApi().qrcodeCreateTemporary(1L, 10000);
        Wechatboot.promotionApi().qrcodeCreatePerpetual(1L);
        QrcodeCreateResponse qrcodeCreateResponse = Wechatboot.promotionApi().qrcodeCreatePerpetual("1");
        ShowqrcodeResponse showqrcodeResponse = Wechatboot.promotionApi().showqrcode(qrcodeCreateResponse.getTicket());
        System.out.println("测试 创建及下载二维码" + showqrcodeResponse.getFile().getAbsolutePath());
    }

    @Test
    public void testShorturl() throws WechatException {
        ShorturlResponse response = Wechatboot.promotionApi().shorturl("http://mp.weixin.qq.com/wiki/6/856aaeb492026466277ea39233dc23ee.html");
        System.out.println("测试 长链接转短链接接口" + response.getShortUrl());
    }
}
