package com.uncos.wechatboot.base;

/**
 * 微信帐号接口
 * Created by xuwen on 2016/1/14.
 */
public interface AccountInterface extends AppidInterface {

    /**
     * 获取微信的appSecret
     *
     * @return
     */
    String getSecret();
}
