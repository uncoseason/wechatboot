package com.uncos.wechatboot.utils;

import com.uncos.wechatboot.api.Wechatboot;
import com.uncos.wechatboot.api.base.protocol.access_token.AccessTokenResponse;
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

    private static AccessTokenResponse AccessTokenResponse;

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
                    synchronized (AccessTokenResponse) {
                        AccessTokenResponse = null;
                        accessToken();
                    }
                }
            }, AccessTokenResponse.getExpiresIn() * 1000L);
        }
        return AccessTokenResponse.getAccessToken();
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
