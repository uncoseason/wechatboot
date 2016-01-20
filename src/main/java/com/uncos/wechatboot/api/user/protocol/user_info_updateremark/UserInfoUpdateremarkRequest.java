package com.uncos.wechatboot.api.user.protocol.user_info_updateremark;

import com.uncos.wechatboot.base.request.BaseOpenidRequest;

/**
 * Created by xuwen on 2016/1/17.
 */
public class UserInfoUpdateremarkRequest extends BaseOpenidRequest {

    private String remark;

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
