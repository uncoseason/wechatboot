package com.uncos.wechatboot.api;

import com.uncos.wechatboot.api.base.BaseApi;
import com.uncos.wechatboot.api.js.JsApi;
import com.uncos.wechatboot.api.material.MaterialApi;
import com.uncos.wechatboot.api.message.MessageApi;
import com.uncos.wechatboot.api.pay.PayApi;
import com.uncos.wechatboot.api.promotion.PromotionApi;
import com.uncos.wechatboot.api.user.UserApi;
import com.uncos.wechatboot.common.Config;
import org.apache.http.Consts;

import java.nio.charset.Charset;

/**
 * 统一API
 * Created by xuwen on 2016/1/15.
 */
public class Wechatboot {

    /**
     * 统一编码
     *
     * @return
     */
    public static Charset charset() {
        return Consts.UTF_8;
    }

    /**
     * 配置对象
     *
     * @return
     */
    public static Config config() {
        return Config.instance();
    }

    /**
     * 基础支持API
     *
     * @return
     */
    public static BaseApi baseApi() {
        return BaseApi.instance();
    }

    /**
     * 用户管理API
     *
     * @return
     */
    public static UserApi userApi() {
        return UserApi.instance();
    }

    /**
     * 推广支持API
     *
     * @return
     */
    public static PromotionApi promotionApi() {
        return PromotionApi.instance();
    }

    /**
     * 客户接口主动发送消息API
     *
     * @return
     */
    public static MessageApi messageApi() {
        return MessageApi.instance();
    }

    /**
     * 素材API
     *
     * @return
     */
    public static MaterialApi materialApi() {
        return MaterialApi.instance();
    }

    /**
     * 支付API
     *
     * @return
     */
    public static PayApi payApi() {
        return PayApi.instance();
    }

    /**
     * JS API
     *
     * @return
     */
    public static JsApi jsApi() {
        return JsApi.instance();
    }
}
