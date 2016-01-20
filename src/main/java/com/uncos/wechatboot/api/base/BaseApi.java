package com.uncos.wechatboot.api.base;

import com.uncos.wechatboot.api.base.protocol.access_token.AccessTokenRequest;
import com.uncos.wechatboot.api.base.protocol.access_token.AccessTokenResponse;
import com.uncos.wechatboot.api.base.protocol.getcallbackip.GetcallbackipRequest;
import com.uncos.wechatboot.api.base.protocol.getcallbackip.GetcallbackipResponse;
import com.uncos.wechatboot.exception.WechatException;
import com.uncos.wechatboot.utils.Checker;
import com.uncos.wechatboot.utils.Converter;
import com.uncos.wechatboot.utils.Http;

/**
 * 基础支持API
 * Created by xuwen on 2016/1/14.
 */
public class BaseApi {

    /*获取access token*/
    private static final String API_ACCESS_TOKEN = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
    /*获取微信服务器IP地址*/
    private static final String API_GETCALLBACKIP = "https://api.weixin.qq.com/cgi-bin/getcallbackip?access_token=ACCESS_TOKEN";

    private static BaseApi instance = new BaseApi();

    public static BaseApi instance() {
        return instance;
    }

    /**
     * 获取access token
     *
     * @param request
     * @return
     * @throws WechatException
     */
    public AccessTokenResponse accessToken(AccessTokenRequest request) throws WechatException {
        String result = Http.get(API_ACCESS_TOKEN, request);
        Checker.checkApiResponse(result);
        return Converter.fromJSON(result, AccessTokenResponse.class);
    }

    /**
     * 获取access token
     *
     * @return
     * @throws WechatException
     */
    public AccessTokenResponse accessToken() throws WechatException {
        AccessTokenRequest request = new AccessTokenRequest();
        return accessToken(request);
    }

    /**
     * 获取微信服务器IP地址
     *
     * @param request
     * @return
     * @throws WechatException
     */
    public GetcallbackipResponse getcallbackip(GetcallbackipRequest request) throws WechatException {
        String result = Http.get(API_GETCALLBACKIP, request);
        Checker.checkApiResponse(result);
        return Converter.fromJSON(result, GetcallbackipResponse.class);
    }

    /**
     * 获取微信服务器IP地址
     *
     * @return
     * @throws WechatException
     */
    public GetcallbackipResponse getcallbackip() throws WechatException {
        GetcallbackipRequest request = new GetcallbackipRequest();
        return getcallbackip(request);
    }
}
