package com.uncos.wechatboot.api;

import com.uncos.wechatboot.api.base.protocol.access_token.AccessTokenResponse;
import com.uncos.wechatboot.api.base.protocol.getcallbackip.GetcallbackipRequest;
import com.uncos.wechatboot.common.Config;
import com.uncos.wechatboot.exception.WechatException;
import org.junit.Test;

/**
 * Created by xuwen on 2016/1/15.
 */
public class BaseApiTest {

    @Test
    public void testToken() throws WechatException {
        Wechatboot.baseApi().accessToken();
        System.out.println("测试 获取token");
    }

    @Test
    public void testCodeConfig() throws WechatException {
        Config.instance().setAppid("wxb787f36435a39be2");
        Config.instance().setSecret("be3249703b893983b2dc163a84aa9aaf");
        Config.instance().setToken("edubossPlatform");
        Wechatboot.baseApi().accessToken();
        System.out.println("测试 代码配置");
    }

    @Test
    public void testGetcallbackip() throws WechatException {
        Wechatboot.baseApi().getcallbackip();
        System.out.println("测试 获取微信服务器IP地址");
    }

    @Test
    public void testTwoToken() throws WechatException {
        AccessTokenResponse token = Wechatboot.baseApi().accessToken();
        GetcallbackipRequest request = new GetcallbackipRequest();
        request.setAccessToken(token.getAccessToken());
        Wechatboot.baseApi().getcallbackip(request);
        System.out.println("测试 同时两个access_token");
    }

}
