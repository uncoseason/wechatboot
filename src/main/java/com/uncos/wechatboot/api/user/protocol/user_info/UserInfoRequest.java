package com.uncos.wechatboot.api.user.protocol.user_info;

import com.uncos.wechatboot.base.request.BaseAccessTokenRequest;
import com.uncos.wechatboot.common.Lang;

/**
 * Created by xuwen on 2016/1/17.
 */
public class UserInfoRequest extends BaseAccessTokenRequest{

    private String openid;
    private Lang lang = Lang.zh_CN;

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public Lang getLang() {
        return lang;
    }

    public void setLang(Lang lang) {
        this.lang = lang;
    }
}
