package com.uncos.wechatboot.utils;

import com.uncos.wechatboot.api.Wechatboot;
import com.uncos.wechatboot.api.base.protocol.access_token.AccessTokenResponse;
import com.uncos.wechatboot.api.base.protocol.api_ticket.ApiTicketResponse;
import com.uncos.wechatboot.exception.WechatException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Timer;
import java.util.TimerTask;

/**
 * 凭据中心
 * Created by xuwen on 2016/1/15.
 */
public class CredentialCenter {

    private static Logger logger = LoggerFactory.getLogger(CredentialCenter.class);

    private static final Timer timer = new Timer();
    private static final int aheadOfTime = 60000; // 提前刷新token，单位是毫秒,token的有效期默认为7200000毫秒

    private static AccessTokenResponse AccessTokenResponse;
    private static ApiTicketResponse jsapiTicketResponse;
    private static ApiTicketResponse wxCardTicketResponse;

    /**
     * 获取access_token
     *
     * @return
     */
    public static String accessToken() {
        if (AccessTokenResponse == null) {
            try {
                AccessTokenResponse = Wechatboot.baseApi().accessToken();
            } catch (WechatException e) {
                throw new RuntimeException("接口调用凭据获取失败", e);
            }
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    logger.info("自动刷新access_token");
                    synchronized (AccessTokenResponse) {
                        AccessTokenResponse = null;
                        accessToken();
                    }
                }
            }, AccessTokenResponse.getExpiresIn() * 1000L - aheadOfTime);
        }
        return AccessTokenResponse.getAccessToken();
    }

    /**
     * 获取jsapi ticket
     *
     * @return
     */
    public static String jsapiTicket() {
        if (jsapiTicketResponse == null) {
            try {
                jsapiTicketResponse = Wechatboot.baseApi().jsapiTicket();
            } catch (WechatException e) {
                throw new RuntimeException("接口调用凭据获取失败", e);
            }
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    logger.info("自动刷新jsapi ticket");
                    synchronized (jsapiTicketResponse) {
                        jsapiTicketResponse = null;
                        jsapiTicket();
                    }
                }
            }, jsapiTicketResponse.getExpiresIn() * 1000L - aheadOfTime);
        }
        return jsapiTicketResponse.getTicket();
    }

    /**
     * 获取微信卡券ticket
     *
     * @return
     */
    public static String wxCardTicket() {
        if (wxCardTicketResponse == null) {
            try {
                wxCardTicketResponse = Wechatboot.baseApi().wxCardTicket();
            } catch (WechatException e) {
                throw new RuntimeException("接口调用凭据获取失败", e);
            }
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    logger.info("自动刷新微信卡券ticket");
                    synchronized (wxCardTicketResponse) {
                        wxCardTicketResponse = null;
                        accessToken();
                    }
                }
            }, wxCardTicketResponse.getExpiresIn() * 1000L - aheadOfTime);
        }
        return wxCardTicketResponse.getTicket();
    }

    /**
     * 给URL绑定accessToken参数
     *
     * @param url
     * @return
     */
    public static String writeAccessTokenURL(String url) {
        return url.substring(0, url.indexOf("?")) + "?access_token=" + accessToken();
    }

}
