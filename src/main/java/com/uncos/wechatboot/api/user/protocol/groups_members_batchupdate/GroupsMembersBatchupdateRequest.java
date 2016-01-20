package com.uncos.wechatboot.api.user.protocol.groups_members_batchupdate;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.uncos.wechatboot.base.request.BaseAccessTokenRequest;

import java.util.List;

/**
 * Created by xuwen on 2016/1/16.
 */
public class GroupsMembersBatchupdateRequest extends BaseAccessTokenRequest {

    private List<String> openidList;

    private int toGroupid;

    @JsonProperty("openid_list")
    public List<String> getOpenidList() {
        return openidList;
    }

    public void setOpenidList(List<String> openidList) {
        this.openidList = openidList;
    }

    @JsonProperty("to_groupid")
    public int getToGroupid() {
        return toGroupid;
    }

    public void setToGroupid(int toGroupid) {
        this.toGroupid = toGroupid;
    }
}
