package com.uncos.wechatboot.api.user.protocol.user_info;

import com.uncos.wechatboot.base.request.BaseOpenidRequest;
import com.uncos.wechatboot.common.Lang;

/**
 * Created by xuwen on 2016/1/17.
 */
public class UserInfoRequest extends BaseOpenidRequest {

    private Lang lang = Lang.zh_CN;

    public Lang getLang() {
        return lang;
    }

    public void setLang(Lang lang) {
        this.lang = lang;
    }
}
