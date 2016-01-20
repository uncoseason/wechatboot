package com.uncos.wechatboot.common;

/**
 * OAuth授权类型
 * Created by xuwen on 2016/1/21.
 */
public enum OAuthGrantType {
    /**
     * 授权code
     */
    authorization_code,
    /**
     * 刷新token
     */
    refresh_token,
}
