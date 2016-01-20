package com.uncos.wechatboot.api.user.protocol.groups_members_update;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.uncos.wechatboot.base.request.BaseOpenidRequest;

/**
 * Created by xuwen on 2016/1/16.
 */
public class GroupsMembersUpdateRequest extends BaseOpenidRequest {

    private int toGroupid;

    @JsonProperty("to_groupid")
    public int getToGroupid() {
        return toGroupid;
    }

    public void setToGroupid(int toGroupid) {
        this.toGroupid = toGroupid;
    }
}
